import { useState } from 'react';
import {
    TextField,
    Button,
    Box,
    Typography,
    Checkbox,
    FormControlLabel,
    FormLabel,
    RadioGroup,
    Radio
} from '@mui/material';
import axios from 'axios';

export default function ItineraryForm() {
    const [formData, setFormData] = useState({
        userId: '',
        countryId: '',
        orderOnTrip: 1,
        countryOfOrigin: false,
        startDate: '',
        endDate: '',
        daysSpentInCountry: 1,
        userWantsCurrencyExchangeRate: false
    });

    const countries = [
        { id: 1, name: "Afghanistan" },
        { id: 2, name: "Albania" },
        { id: 3, name: "Algeria" },
        { id: 4, name: "Andorra" },
        { id: 5, name: "Angola" },
        { id: 6, name: "Antigua and Barbuda" },
        { id: 7, name: "Argentina" },
        { id: 8, name: "Armenia" },
        { id: 9, name: "Australia" },
        { id: 10, name: "Austria" },
        { id: 11, name: "Azerbaijan" },
        { id: 12, name: "The Bahamas" },
        { id: 13, name: "Bahrain" },
        { id: 14, name: "Bangladesh" },
        { id: 15, name: "Barbados" },
        { id: 16, name: "Belarus" },
        { id: 17, name: "Belgium" },
        { id: 18, name: "Belize" },
        { id: 19, name: "Benin" },
        { id: 20, name: "Bhutan" },
        { id: 21, name: "Bolivia" },
        { id: 22, name: "Bosnia and Herzegovina" },
        { id: 23, name: "Botswana" },
        { id: 24, name: "Brazil" },
        { id: 25, name: "Brunei" },
        { id: 26, name: "Bulgaria" },
        { id: 27, name: "Burkina Faso" },
        { id: 28, name: "Burundi" },
        { id: 29, name: "Cambodia" },
        { id: 30, name: "Cameroon" },
        { id: 31, name: "Canada" },
        { id: 32, name: "Cape Verde" },
        { id: 33, name: "Central African Republic" },
        { id: 34, name: "Chad" },
        { id: 35, name: "Chile" },
        { id: 36, name: "China" },
        { id: 37, name: "Colombia" },
        { id: 38, name: "Comoros" },
        { id: 39, name: "Republic of the Congo" },
        { id: 40, name: "Costa Rica" },
        { id: 41, name: "Cote d’Ivoire" },
        { id: 42, name: "Croatia" },
        { id: 43, name: "Cuba" },
        { id: 44, name: "Cyprus" },
        { id: 45, name: "Czech Republic" },
        { id: 46, name: "Denmark" },
        { id: 47, name: "Djibouti" },
        { id: 48, name: "Dominica" },
        { id: 49, name: "Dominican Republic" },
        { id: 50, name: "East Timor (Timor-Leste)" },
        { id: 51, name: "Ecuador" },
        { id: 52, name: "Egypt" },
        { id: 53, name: "El Salvador" },
        { id: 54, name: "Equatorial Guinea" },
        { id: 55, name: "Eritrea" },
        { id: 56, name: "Estonia" },
        { id: 57, name: "Ethiopia" },
        { id: 58, name: "Fiji" },
        { id: 59, name: "Finland" },
        { id: 60, name: "France" },
        { id: 61, name: "Gabon" },
        { id: 62, name: "The Gambia" },
        { id: 63, name: "Georgia" },
        { id: 64, name: "Germany" },
        { id: 65, name: "Ghana" },
        { id: 66, name: "Greece" },
        { id: 67, name: "Grenada" },
        { id: 68, name: "Guatemala" },
        { id: 69, name: "Guinea" },
        { id: 70, name: "Guinea-Bissau" },
        { id: 71, name: "Guyana" },
        { id: 72, name: "Haiti" },
        { id: 73, name: "Honduras" },
        { id: 74, name: "Hungary" },
        { id: 75, name: "Iceland" },
        { id: 76, name: "India" },
        { id: 77, name: "Indonesia" },
        { id: 78, name: "Iran" },
        { id: 79, name: "Iraq" },
        { id: 80, name: "Ireland" },
        { id: 81, name: "Israel" },
        { id: 82, name: "Italy" },
        { id: 83, name: "Jamaica" },
        { id: 84, name: "Japan" },
        { id: 85, name: "Jordan" },
        { id: 86, name: "Kazakhstan" },
        { id: 87, name: "Kenya" },
        { id: 88, name: "Kiribati" },
        { id: 89, name: "North Korea" },
        { id: 90, name: "South Korea" },
        { id: 91, name: "Kuwait" },
        { id: 92, name: "Kyrgyzstan" },
        { id: 93, name: "Laos" },
        { id: 94, name: "Latvia" },
        { id: 95, name: "Lebanon" },
        { id: 96, name: "Lesotho" },
        { id: 97, name: "Liberia" },
        { id: 98, name: "Libya" },
        { id: 99, name: "Liechtenstein" },
        { id: 100, name: "Lithuania" },
        { id: 101, name: "Luxembourg" },
        { id: 102, name: "Macedonia" },
        { id: 103, name: "Madagascar" },
        { id: 104, name: "Malawi" },
        { id: 105, name: "Malaysia" },
        { id: 106, name: "Maldives" },
        { id: 107, name: "Mali" },
        { id: 108, name: "Malta" },
        { id: 109, name: "Marshall Islands" },
        { id: 110, name: "Mauritania" },
        { id: 111, name: "Mauritius" },
        { id: 112, name: "Mexico" },
        { id: 113, name: "Federated States of Micronesia" },
        { id: 114, name: "Moldova" },
        { id: 115, name: "Monaco" },
        { id: 116, name: "Mongolia" },
        { id: 117, name: "Montenegro" },
        { id: 118, name: "Morocco" },
        { id: 119, name: "Mozambique" },
        { id: 120, name: "Myanmar (Burma)" },
        { id: 121, name: "Namibia" },
        { id: 122, name: "Nauru" },
        { id: 123, name: "Nepal" },
        { id: 124, name: "Netherlands" },
        { id: 125, name: "New Zealand" },
        { id: 126, name: "Nicaragua" },
        { id: 127, name: "Niger" },
        { id: 128, name: "Nigeria" },
        { id: 129, name: "Norway" },
        { id: 130, name: "Oman" },
        { id: 131, name: "Pakistan" },
        { id: 132, name: "Palau" },
        { id: 133, name: "Palestine" },
        { id: 134, name: "Panama" },
        { id: 135, name: "Papua New Guinea" },
        { id: 136, name: "Paraguay" },
        { id: 137, name: "Peru" },
        { id: 138, name: "Philippines" },
        { id: 139, name: "Poland" },
        { id: 140, name: "Portugal" },
        { id: 141, name: "Qatar" },
        { id: 142, name: "Romania" },
        { id: 143, name: "Russia" },
        { id: 144, name: "Rwanda" },
        { id: 145, name: "Saint Kitts and Nevis" },
        { id: 146, name: "Saint Lucia" },
        { id: 147, name: "Saint Vincent and the Grenadines" },
        { id: 148, name: "Samoa" },
        { id: 149, name: "San Marino" },
        { id: 150, name: "Sao Tome and Principe" },
        { id: 151, name: "Saudi Arabia" },
        { id: 152, name: "Senegal" },
        { id: 153, name: "Serbia" },
        { id: 154, name: "Seychelles" },
        { id: 155, name: "Sierra Leone" },
        { id: 156, name: "Singapore" },
        { id: 157, name: "Slovakia" },
        { id: 158, name: "Slovenia" },
        { id: 159, name: "Solomon Islands" },
        { id: 160, name: "Somalia" },
        { id: 161, name: "South Africa" },
        { id: 162, name: "South Sudan" },
        { id: 163, name: "Spain" },
        { id: 164, name: "Sri Lanka" },
        { id: 165, name: "Sudan" },
        { id: 166, name: "Suriname" },
        { id: 167, name: "Swaziland" },
        { id: 168, name: "Sweden" },
        { id: 169, name: "Switzerland" },
        { id: 170, name: "Syria" },
        { id: 171, name: "Taiwan" },
        { id: 172, name: "Tajikistan" },
        { id: 173, name: "Tanzania" },
        { id: 174, name: "Thailand" },
        { id: 175, name: "Togo" },
        { id: 176, name: "Tonga" },
        { id: 177, name: "Trinidad and Tobago" },
        { id: 178, name: "Tunisia" },
        { id: 179, name: "Turkey" },
        { id: 180, name: "Turkmenistan" },
        { id: 181, name: "Tuvalu" },
        { id: 182, name: "Uganda" },
        { id: 183, name: "Ukraine" },
        { id: 184, name: "United Arab Emirates" },
        { id: 185, name: "United Kingdom" },
        { id: 186, name: "United States of America" },
        { id: 187, name: "Uruguay" },
        { id: 188, name: "Uzbekistan" },
        { id: 189, name: "Vanuatu" },
        { id: 190, name: "Vatican City (Holy See)" },
        { id: 191, name: "Venezuela" },
        { id: 192, name: "Vietnam" },
        { id: 193, name: "Yemen" },
        { id: 194, name: "Zambia" },
        { id: 195, name: "Zimbabwe" },
    ];

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: type === 'checkbox' ? checked : value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!formData.countryId) {
            alert("Please select a country.");
            return;
        }

        console.log(formData)

        try {
            const response = await axios.post('http://localhost:8080/api/itineraries/create', formData);
            console.log("Itinerary submitted successfully:", response.data);
            alert(`Itinerary submitted with ID: ${response.data.id}`);
        } catch (error) {
            console.error("Error submitting itinerary:", error);
            alert("There was an error submitting your itinerary.");
        }
    };

    return (
        <div style={{ backgroundColor: '#e6ffe6', padding: '2rem' }}>
            <Box component="form" onSubmit={handleSubmit} sx={{ m: 4 }}>
                <Typography variant="h5" gutterBottom>
                    Itinerary Form
                </Typography>

                <TextField
                    label="User Id"
                    name="userId"
                    type="number"
                    value={formData.userId}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />

                <TextField
                    label=""
                    name="countryId"
                    value={formData.countryId}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                    select
                    SelectProps={{
                        native: true
                    }}
                >
                    <option value="">Select a country</option>
                    {countries.map((country) => (
                        <option key={country.id} value={country.id}>
                            {country.name}
                        </option>
                    ))}
                </TextField>

                <TextField
                    label="Order on Trip"
                    name="orderOnTrip"
                    type="number"
                    value={formData.orderOnTrip}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />

                <FormControlLabel
                    control={
                        <Checkbox
                            name="countryOfOrigin"
                            checked={formData.countryOfOrigin}
                            onChange={handleChange}
                        />
                    }
                    label="Is this the country of origin?"
                />

                <TextField
                    label="Start Date"
                    name="startDate"
                    type="date"
                    value={formData.startDate}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    InputLabelProps={{
                        shrink: true
                    }}
                    required
                />

                <TextField
                    label="End Date"
                    name="endDate"
                    type="date"
                    value={formData.endDate}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    InputLabelProps={{
                        shrink: true
                    }}
                    required
                />

                <TextField
                    label="Days Spent in Country"
                    name="daysSpentInCountry"
                    type="number"
                    value={formData.daysSpentInCountry}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />

                <FormLabel component="legend">Do you want the currency exchange rate?</FormLabel>
                <RadioGroup
                    name="userWantsCurrencyExchangeRate"
                    value={String(formData.userWantsCurrencyExchangeRate)}
                    onChange={handleChange}
                    row
                >
                    <FormControlLabel value="true" control={<Radio />} label="Yes" />
                    <FormControlLabel value="false" control={<Radio />} label="No" />
                </RadioGroup>

                <Button type="submit" variant="contained" sx={{ mt: 2 }}>
                    Submit Itinerary
                </Button>
            </Box>
        </div>

    );
}
