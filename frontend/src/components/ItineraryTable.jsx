import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
    Table, TableHead, TableBody, TableRow, TableCell,
    FormControl, InputLabel, Select, MenuItem, Paper
} from '@mui/material';

const ItineraryTable = () => {
    const [users, setUsers] = useState([]);
    const [itineraries, setItineraries] = useState([]);
    const [selectedUserId, setSelectedUserId] = useState('');

    useEffect(() => {
        // Fetch users for dropdown
        axios.get('http://localhost:8080/api/userInfo')
            .then(res => setUsers(res.data))
            .catch(err => console.error(err));
    }, []);

    useEffect(() => {
        console.log("Get By userId: ", users);
        if (users === null)
            return
        // Fetch itineraries (optionally filtered)
        const url = selectedUserId
            ? `http://localhost:8080/api/itineraries/user/${selectedUserId}`
            : 'http://localhost:8080/api/itineraries';
        axios.get(url)
            .then(res => setItineraries(res.data))
            .catch(err => console.error(err));
    }, [selectedUserId]);



    return (
        <Paper style={{ padding: '1rem' }}>
            <FormControl fullWidth margin="normal">
                <InputLabel>User</InputLabel>
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
                    </TableRow>
                </TableHead>
                <TableBody>
                    {itineraries.map((itinerary) => (
                        <TableRow key={itinerary.id}>
                            <TableCell>{itinerary.id}</TableCell>
                            <TableCell>{itinerary.userId}</TableCell>
                            <TableCell>{itinerary.countryId}</TableCell>
                            <TableCell>{itinerary.startDate}</TableCell>
                            <TableCell>{itinerary.endDate}</TableCell>
                            <TableCell>{itinerary.daysSpentInCountry}</TableCell>
                            <TableCell>{itinerary.countryOfOrigin ? 'Yes' : 'No'}</TableCell>
                            <TableCell>{itinerary.userWantsCurrencyExchangeRate ? 'Yes' : 'No'}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </Paper>
    );
};

export default ItineraryTable;