import React from 'react';
import {BrowserRouter as Router, Routes, Route, NavLink} from 'react-router-dom';
import UserInfoForm from './components/UserInfoForm.jsx';
import ItineraryForm from './components/ItineraryForm.jsx';
import ItineraryTable from './components/ItineraryTable.jsx';
import EditItinerary from './components/EditItinerary.jsx';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import bgImage from './assets/money_bacground.jpg';


export default function App() {
    const linkStyle = {
        color: 'inherit',
        textDecoration: 'none',
        marginRight: '16px',
    };

    const appStyle = {
        minHeight: '100vh',
        backgroundImage: `url(${bgImage})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat',
    };
    return (
        <Router>
            <div style={appStyle}>
                <AppBar position="fixed" sx={{ height: 64, justifyContent: 'center' }}>
                    <Toolbar sx={{ minHeight: 64, display: 'flex', justifyContent: 'space-between' }}>
                        <Typography variant="h6" component="div">
                            Travel Planner
                        </Typography>
                        <Box>
                            {['/form1', '/form2', '/form3'].map((path, i) => (
                                <NavLink
                                    key={path}
                                    to={path}
                                    style={({ isActive }) => ({
                                        ...linkStyle,
                                        borderBottom: isActive ? '2px solid white' : 'none',
                                        fontWeight: isActive ? 'bold' : 'normal',
                                    })}
                                >
                                    <Button color="inherit">
                                        {['User Info', 'Itinerary Form', 'Itinerary Table'][i]}
                                    </Button>
                                </NavLink>
                            ))}
                        </Box>
                    </Toolbar>
                </AppBar>


                <Box sx={{ mt: '64px', p: 2 }}>
                    <Routes>
                        <Route path="/form1" element={<UserInfoForm />} />
                        <Route path="/form2" element={<ItineraryForm />} />
                        <Route path="/form3" element={<ItineraryTable />} />
                        <Route path="/edit-itinerary/:id" element={<EditItinerary />} />
                    </Routes>
                </Box>
            </div>
        </Router>
    );
}
