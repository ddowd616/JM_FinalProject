import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import UserInfoForm from './components/UserInfoForm.jsx';
import AnotherForm from './components/ItineraryForm.jsx';
import {Button} from "@mui/material";
import ItineraryForm from "./components/ItineraryForm.jsx";

export default function App() {
    return (
        <Router>
            <div>
                <nav>
                    <Link to="/form1">
                        <Button variant="outlined" sx={{ mr: 2 }}>
                            Go to User Info Form
                        </Button>
                    </Link>
                    <Link to="/form2">
                        <Button variant="outlined">
                            Go to Itinerary Form
                        </Button>
                    </Link>
                </nav>

                <Routes>
                    <Route path="/form1" element={<UserInfoForm />} />
                    <Route path="/form2" element={<ItineraryForm />} />
                </Routes>
            </div>
        </Router>
    );
}
