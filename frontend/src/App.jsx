import React, {useRef, useState} from 'react';
import {BrowserRouter as Router, Routes, Route, NavLink} from 'react-router-dom';
import UserInfoForm from './components/UserInfoForm.jsx';
import ItineraryForm from './components/ItineraryForm.jsx';
import ItineraryTable from './components/ItineraryTable.jsx';
import EditItinerary from './components/EditItinerary.jsx';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import bgImage from './assets/money_bacground.jpg';
import CoinRain from "./components/CoinRain.jsx";
import Home from "./components/Home.jsx";
import coinSound from './assets/728429__sadiquecat__rain-of-coins.wav';
import { playClickSound } from './utils/sound';
import IntroAnimation from "./components/IntroAnimation.jsx";
import './App.css';


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

    const [rain, setRain] = useState(false);
    const audioRef = useRef(null);

    const handleRainToggle = () => {
        if (!rain) {
            audioRef.current = new Audio(coinSound);
            audioRef.current.loop = true;
            audioRef.current.play().catch(e => console.error('Sound playback failed:', e));
        } else {
            audioRef.current?.pause();
            audioRef.current = null;
        }
        setRain(!rain);
    };

    return (
        <Router>
            <div style={appStyle}>
                {rain && <CoinRain/>}
                <AppBar position="fixed" sx={{ height: 64, justifyContent: 'center' }}>
                    <Toolbar sx={{ minHeight: 64, display: 'flex', justifyContent: 'space-between' }}>
                        <Typography variant="h6" component="div">
                            Travel Planner
                        </Typography>
                        <Box>
                            {['/','/form1', '/form2', '/form3'].map((path, i) => (
                                <NavLink
                                    key={path}
                                    to={path}
                                    onClick={playClickSound}
                                    style={({ isActive }) => ({
                                        ...linkStyle,
                                        borderBottom: isActive ? '2px solid white' : 'none',
                                        fontWeight: isActive ? 'bold' : 'normal',
                                    })}
                                >
                                    <Button color="inherit">
                                        {['Home','User Info', 'Itinerary Form', 'Itinerary Table'][i]}
                                    </Button>
                                </NavLink>
                            ))}
                        </Box>
                        <Button color="inherit" onClick={handleRainToggle}>
                            {rain ? 'Clear' : 'Make it Rain'}
                        </Button>

                    </Toolbar>
                </AppBar>
                {location.pathname === '/' && <IntroAnimation />}

                <Box sx={{ mt: '64px', p: 2 }}>
                    <Routes>
                        <Route path="/form1" element={<UserInfoForm />} />
                        <Route path="/form2" element={<ItineraryForm />} />
                        <Route path="/form3" element={<ItineraryTable />} />
                        <Route path="/edit-itinerary/:id" element={<EditItinerary />} />
                        <Route path="/" element={<Home />} />
                    </Routes>
                </Box>

            </div>
        </Router>
    );
}
