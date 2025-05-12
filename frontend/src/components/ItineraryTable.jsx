import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
    Table, TableHead, TableBody, TableRow, TableCell,
    FormControl, Select, MenuItem, Paper
} from '@mui/material';

const ItineraryTable = () => {
    const [users, setUsers] = useState([]);
    const [itineraries, setItineraries] = useState([]);
    const [selectedUserId, setSelectedUserId] = useState('');
    const [countries, setCountries] = useState([]);
    const [exchangeRates, setExchangeRates] = useState({});

    useEffect(() => {
        axios.get('http://localhost:8080/api/userInfo')
            .then(res => setUsers(res.data))
            .catch(err => console.error(err));
    }, []);

    useEffect(() => {
        axios.get('http://localhost:8080/api/country')
            .then(res => setCountries(res.data))
            .catch(err => console.error(err));
    }, []);

    useEffect(() => {
        const url = selectedUserId
            ? `http://localhost:8080/api/itineraries/user/${selectedUserId}`
            : 'http://localhost:8080/api/itineraries';

        axios.get(url)
            .then(res => setItineraries(res.data))
            .catch(err => console.error(err));
    }, [selectedUserId]);

    useEffect(() => {
        if (!countries.length || !itineraries.length) return;

        const originItinerary = itineraries.find(it => it.countryOfOrigin);
        if (!originItinerary) return;

        const originCountry = countries.find(c => String(c.id) === String(originItinerary.countryId));
        if (!originCountry || !originCountry.currencyCode) return;

        const originCurrencyCode = originCountry.currencyCode;

        // Collect destination currency codes
        const destinationCurrencyCodes = [
            ...new Set(
                itineraries
                    .filter(it => !it.countryOfOrigin && it.userWantsCurrencyExchangeRate)
                    .map(it => {
                        const country = countries.find(c => String(c.id) === String(it.countryId));
                        return country?.currencyCode;
                    })
                    .filter(code => code && code !== originCurrencyCode)
            )
        ];

        // If origin wants rate to USD, include USD
        if (originItinerary.userWantsCurrencyExchangeRate && !destinationCurrencyCodes.includes('USD')) {
            destinationCurrencyCodes.push('USD');
        }

        if (!destinationCurrencyCodes.length) return;

        // Fetch exchange rates using USD as source
        axios.get(`https://apilayer.net/api/live?access_key=6444e2260317901cb0f2300ab988ef2a&currencies=${[originCurrencyCode, ...destinationCurrencyCodes].join(',')}&source=USD&format=1`)
            .then(res => {
                const usdQuotes = res.data.quotes;
                const newExchangeRates = {};

                destinationCurrencyCodes.forEach(dest => {
                    const usdToDest = usdQuotes[`USD${dest}`];
                    const usdToOrigin = usdQuotes[`USD${originCurrencyCode}`];

                    if (originCurrencyCode === dest) {
                        newExchangeRates[`${originCurrencyCode}${dest}`] = "1.00";
                    } else if (dest === "USD") {
                        if (usdToOrigin) {
                            const rate = (1 / usdToOrigin).toFixed(8); // origin → USD
                            newExchangeRates[`${originCurrencyCode}USD`] = rate;
                        } else {
                            newExchangeRates[`${originCurrencyCode}USD`] = 'N/A';
                        }
                    } else if (usdToDest && usdToOrigin) {
                        const rate = (usdToDest / usdToOrigin).toFixed(8); // origin → dest
                        newExchangeRates[`${originCurrencyCode}${dest}`] = rate;
                    } else {
                        newExchangeRates[`${originCurrencyCode}${dest}`] = 'N/A';
                    }
                });

                setExchangeRates(newExchangeRates);
            })
            .catch(err => {
                console.error("Error fetching exchange rates:", err);
            });
    }, [countries, itineraries]);

    const getExchangeRate = (originCode, destCode) => {
        const pair = `${originCode}${destCode}`;
        return exchangeRates[pair] || 'N/A';
    };

    const originItinerary = itineraries.find(it => it.countryOfOrigin === true);
    const originCountry = countries.find(c => c.id === originItinerary?.countryId);
    const originCurrencyCode = originCountry?.currencyCode || '';

    return (
        <Paper style={{ padding: '1rem' }}>
            <FormControl fullWidth margin="normal">
                <Select
                    value={selectedUserId}
                    onChange={(e) => setSelectedUserId(e.target.value)}
                    displayEmpty
                >
                    <MenuItem value="">All Users</MenuItem>
                    {users.map((user) => (
                        <MenuItem key={user.id} value={String(user.userId)}>
                            {user.userName} (ID: {user.userId})
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>

            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Itinerary ID</TableCell>
                        <TableCell>User ID</TableCell>
                        <TableCell>Country ID</TableCell>
                        <TableCell>Start Date</TableCell>
                        <TableCell>End Date</TableCell>
                        <TableCell>Days</TableCell>
                        <TableCell>Origin?</TableCell>
                        <TableCell>Wants Exchange Rate?</TableCell>
                        <TableCell>Exchange Rate</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {itineraries.map((itinerary) => {
                        const destCountry = countries.find(c => c.id === itinerary.countryId);
                        const destCurrency = destCountry?.currencyCode || '';
                        const showRate = itinerary.userWantsCurrencyExchangeRate;

                        let rateDisplay = 'N/A';
                        if (showRate) {
                            if (itinerary.countryOfOrigin) {
                                rateDisplay = `${originCurrencyCode} → USD: ${getExchangeRate(originCurrencyCode, 'USD')}`;
                            } else if (originCurrencyCode !== destCurrency) {
                                rateDisplay = `${originCurrencyCode} → ${destCurrency}: ${getExchangeRate(originCurrencyCode, destCurrency)}`;
                            } else {
                                rateDisplay = '1.00';
                            }
                        }

                        return (
                            <TableRow key={itinerary.id}>
                                <TableCell>{itinerary.id}</TableCell>
                                <TableCell>{itinerary.userId}</TableCell>
                                <TableCell>{itinerary.countryId}</TableCell>
                                <TableCell>{itinerary.startDate}</TableCell>
                                <TableCell>{itinerary.endDate}</TableCell>
                                <TableCell>{itinerary.daysSpentInCountry}</TableCell>
                                <TableCell>{itinerary.countryOfOrigin ? 'Yes' : 'No'}</TableCell>
                                <TableCell>{showRate ? 'Yes' : 'No'}</TableCell>
                                <TableCell>{rateDisplay}</TableCell>
                            </TableRow>
                        );
                    })}
                </TableBody>
            </Table>
        </Paper>
    );
};

export default ItineraryTable;
