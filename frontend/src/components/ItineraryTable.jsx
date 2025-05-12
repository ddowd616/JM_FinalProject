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
        console.log("Get By userId: ", users);
        if (users === null)
            return

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
        if (!originItinerary) {
            console.error("No origin itinerary found.");
            return;
        }

        // console.log("Found origin itinerary:", originItinerary);

        const originCountry = countries.find(c => String(c.id) === String(originItinerary.countryId));

        if (!originCountry) {
            console.error("Origin country not found for countryId:", originItinerary.countryId);
            return;
        }

        // console.log('Found origin country:', originCountry);

        const destinationItinerary = itineraries.find(it => it.id !== originItinerary.id);
        if (!destinationItinerary) {
            console.error("No destination itinerary found.");
            return;
        }

        const destinationCountry = countries.find(c => String(c.id) === String(destinationItinerary.countryId));
        if (!destinationCountry) {
            console.error("Destination country not found for countryId:", destinationItinerary.countryId);
            return;
        }

        // console.log('Found destination country:', destinationCountry);

        const originCurrencyCode = originCountry.currencyCode;
        const destinationCurrencyCode = destinationCountry.currencyCode;



        if (originCurrencyCode && destinationCurrencyCode) {
            axios.get(`https://apilayer.net/api/live?access_key=6444e2260317901cb0f2300ab988ef2a&currencies=${destinationCurrencyCode}&source=${originCurrencyCode}&format=1`)
                .then(res => {
                    console.log(res)
                    // console.log("rate", res.data.quotes.${originCurrencyCode})
                    const quoteKey = `${originCurrencyCode}${destinationCurrencyCode}`;
                    console.log(quoteKey)
                    const exchangeRate = res.data.quotes[quoteKey];
                    console.log(exchangeRate)
                    if (exchangeRate) {
                        setExchangeRates(res.data.quotes);
                    } else {
                        console.error("Exchange rate not found for:", quoteKey);
                        setExchangeRates("N/A");
                    }
                })
                .catch(err => {
                    console.error("Error fetching exchange rate", err);
                    setExchangeRates("N/A");
                });
        } else {
            console.error("Invalid currency codes");
            setExchangeRates("N/A");
        }


    }, [countries, itineraries]);
    const getExchangeRate = (originCode, destCode) => {
        const pair = `${originCode}${destCode}`;
        return exchangeRates[pair] ? exchangeRates[pair].toFixed(2) : 'N/A';
    };

    const originItinerary = itineraries.find(it => it.countryOfOrigin === true);
    const originCountry = countries.find(c => c.id === originItinerary?.countryId);
    const originCurrencyCode = originCountry?.currencyCode || '';

    // console.log('Origin Itinerary:', originItinerary);
    // console.log('Origin Country:', originCountry);
    //
    // console.log("Available country IDs:", countries.map(c => c.id));
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
                        const showRate = itinerary.userWantsCurrencyExchangeRate && originCurrencyCode && destCurrency && originCurrencyCode !== destCurrency;

                        return (
                            <TableRow key={itinerary.id}>
                                <TableCell>{itinerary.id}</TableCell>
                                <TableCell>{itinerary.userId}</TableCell>
                                <TableCell>{itinerary.countryId}</TableCell>
                                <TableCell>{itinerary.startDate}</TableCell>
                                <TableCell>{itinerary.endDate}</TableCell>
                                <TableCell>{itinerary.daysSpentInCountry}</TableCell>
                                <TableCell>{itinerary.countryOfOrigin ? 'Yes' : 'No'}</TableCell>
                                <TableCell>{itinerary.userWantsCurrencyExchangeRate ? 'Yes' : 'No'}</TableCell>
                                <TableCell>
                                    {showRate
                                        ? `${originCurrencyCode} → ${destCurrency}: ${getExchangeRate(originCurrencyCode, destCurrency)}`
                                        : 'N/A'}
                                </TableCell>
                            </TableRow>
                        );
                    })}
                </TableBody>
            </Table>
        </Paper>
    );
};

export default ItineraryTable;