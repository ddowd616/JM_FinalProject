import { useState } from 'react';
import { TextField, Button, MenuItem, Box, Typography } from '@mui/material';
import axios from 'axios';

export default function UserInfoForm() {
    const [form, setForm] = useState({
        userName: '',
        userPassword: '',
        dateOfBirth: '',
        countryId: '' // This will hold the selected country's ID
    });

    // Hard-coded countries list based on the provided data
    const countries = [
        { countryId: 1, countryName: 'Afghanistan' },
        { countryId: 2, countryName: 'Albania' },
        { countryId: 3, countryName: 'Algeria' },
        { countryId: 4, countryName: 'Andorra' },
        { countryId: 5, countryName: 'Angola' },
        { countryId: 6, countryName: 'Antigua and Barbuda' },
        { countryId: 7, countryName: 'Argentina' },
        { countryId: 8, countryName: 'Armenia' },
        { countryId: 9, countryName: 'Australia' },
        { countryId: 10, countryName: 'Austria' },
        { countryId: 11, countryName: 'Azerbaijan' },
        { countryId: 12, countryName: 'The Bahamas' },
        { countryId: 13, countryName: 'Bahrain' },
        { countryId: 14, countryName: 'Bangladesh' },
        { countryId: 15, countryName: 'Barbados' },
        { countryId: 16, countryName: 'Belarus' },
        { countryId: 17, countryName: 'Belgium' },
        { countryId: 18, countryName: 'Belize' },
        { countryId: 19, countryName: 'Benin' },
        { countryId: 20, countryName: 'Bhutan' },
        { countryId: 21, countryName: 'Bolivia' },
        { countryId: 22, countryName: 'Bosnia and Herzegovina' },
        { countryId: 23, countryName: 'Botswana' },
        { countryId: 24, countryName: 'Brazil' },
        { countryId: 25, countryName: 'Brunei' },
        { countryId: 26, countryName: 'Bulgaria' },
        { countryId: 27, countryName: 'Burkina Faso' },
        { countryId: 28, countryName: 'Burundi' },
        { countryId: 29, countryName: 'Cambodia' },
        { countryId: 30, countryName: 'Cameroon' },
        { countryId: 31, countryName: 'Canada' },
        { countryId: 32, countryName: 'Cape Verde' },
        { countryId: 33, countryName: 'Central African Republic' },
        { countryId: 34, countryName: 'Chad' },
        { countryId: 35, countryName: 'Chile' },
        { countryId: 36, countryName: 'China' },
        { countryId: 37, countryName: 'Colombia' },
        { countryId: 38, countryName: 'Comoros' },
        { countryId: 39, countryName: 'Republic of the Congo' },
        { countryId: 40, countryName: 'Costa Rica' },
        { countryId: 41, countryName: 'Cote d’Ivoire' },
        { countryId: 42, countryName: 'Croatia' },
        { countryId: 43, countryName: 'Cuba' },
        { countryId: 44, countryName: 'Cyprus' },
        { countryId: 45, countryName: 'Czech Republic' },
        { countryId: 46, countryName: 'Denmark' },
        { countryId: 47, countryName: 'Djibouti' },
        { countryId: 48, countryName: 'Dominica' },
        { countryId: 49, countryName: 'Dominican Republic' },
        { countryId: 50, countryName: 'East Timor (Timor-Leste)' },
        { countryId: 51, countryName: 'Ecuador' },
        { countryId: 52, countryName: 'Egypt' },
        { countryId: 53, countryName: 'El Salvador' },
        { countryId: 54, countryName: 'Equatorial Guinea' },
        { countryId: 55, countryName: 'Eritrea' },
        { countryId: 56, countryName: 'Estonia' },
        { countryId: 57, countryName: 'Ethiopia' },
        { countryId: 58, countryName: 'Fiji' },
        { countryId: 59, countryName: 'Finland' },
        { countryId: 60, countryName: 'France' },
        { countryId: 61, countryName: 'Gabon' },
        { countryId: 62, countryName: 'The Gambia' },
        { countryId: 63, countryName: 'Georgia' },
        { countryId: 64, countryName: 'Germany' },
        { countryId: 65, countryName: 'Ghana' },
        { countryId: 66, countryName: 'Greece' },
        { countryId: 67, countryName: 'Grenada' },
        { countryId: 68, countryName: 'Guatemala' },
        { countryId: 69, countryName: 'Guinea' },
        { countryId: 70, countryName: 'Guinea-Bissau' },
        { countryId: 71, countryName: 'Guyana' },
        { countryId: 72, countryName: 'Haiti' },
        { countryId: 73, countryName: 'Honduras' },
        { countryId: 74, countryName: 'Hungary' },
        { countryId: 75, countryName: 'Iceland' },
        { countryId: 76, countryName: 'India' },
        { countryId: 77, countryName: 'Indonesia' },
        { countryId: 78, countryName: 'Iran' },
        { countryId: 79, countryName: 'Iraq' },
        { countryId: 80, countryName: 'Ireland' },
        { countryId: 81, countryName: 'Israel' },
        { countryId: 82, countryName: 'Italy' },
        { countryId: 83, countryName: 'Jamaica' },
        { countryId: 84, countryName: 'Japan' },
        { countryId: 85, countryName: 'Jordan' },
        { countryId: 86, countryName: 'Kazakhstan' },
        { countryId: 87, countryName: 'Kenya' },
        { countryId: 88, countryName: 'Kiribati' },
        { countryId: 89, countryName: 'North Korea' },
        { countryId: 90, countryName: 'South Korea' },
        { countryId: 91, countryName: 'Kuwait' },
        { countryId: 92, countryName: 'Kyrgyzstan' },
        { countryId: 93, countryName: 'Laos' },
        { countryId: 94, countryName: 'Latvia' },
        { countryId: 95, countryName: 'Lebanon' },
        { countryId: 96, countryName: 'Lesotho' },
        { countryId: 97, countryName: 'Liberia' },
        { countryId: 98, countryName: 'Libya' },
        { countryId: 99, countryName: 'Liechtenstein' },
        { countryId: 100, countryName: 'Lithuania' },
        { countryId: 101, countryName: 'Luxembourg' },
        { countryId: 102, countryName: 'Macedonia' },
        { countryId: 103, countryName: 'Madagascar' },
        { countryId: 104, countryName: 'Malawi' },
        { countryId: 105, countryName: 'Malaysia' },
        { countryId: 106, countryName: 'Maldives' },
        { countryId: 107, countryName: 'Mali' },
        { countryId: 108, countryName: 'Malta' },
        { countryId: 109, countryName: 'Marshall Islands' },
        { countryId: 110, countryName: 'Mauritania' },
        { countryId: 111, countryName: 'Mauritius' },
        { countryId: 112, countryName: 'Mexico' },
        { countryId: 113, countryName: 'Federated States of Micronesia' },
        { countryId: 114, countryName: 'Moldova' },
        { countryId: 115, countryName: 'Monaco' },
        { countryId: 116, countryName: 'Mongolia' },
        { countryId: 117, countryName: 'Montenegro' },
        { countryId: 118, countryName: 'Morocco' },
        { countryId: 119, countryName: 'Mozambique' },
        { countryId: 120, countryName: 'Myanmar (Burma)' },
        { countryId: 121, countryName: 'Namibia' },
        { countryId: 122, countryName: 'Nauru' },
        { countryId: 123, countryName: 'Nepal' },
        { countryId: 124, countryName: 'Netherlands' },
        { countryId: 125, countryName: 'New Zealand' },
        { countryId: 126, countryName: 'Nicaragua' },
        { countryId: 127, countryName: 'Niger' },
        { countryId: 128, countryName: 'Nigeria' },
        { countryId: 129, countryName: 'Norway' },
        { countryId: 130, countryName: 'Oman' },
        { countryId: 131, countryName: 'Pakistan' },
        { countryId: 132, countryName: 'Palau' },
        { countryId: 133, countryName: 'Panama' },
        { countryId: 134, countryName: 'Papua New Guinea' },
        { countryId: 135, countryName: 'Paraguay' },
        { countryId: 136, countryName: 'Peru' },
        { countryId: 137, countryName: 'Philippines' },
        { countryId: 138, countryName: 'Poland' },
        { countryId: 139, countryName: 'Portugal' },
        { countryId: 140, countryName: 'Qatar' },
        { countryId: 141, countryName: 'Romania' },
        { countryId: 142, countryName: 'Russia' },
        { countryId: 143, countryName: 'Rwanda' },
        { countryId: 144, countryName: 'Saint Kitts and Nevis' },
        { countryId: 145, countryName: 'Saint Lucia' },
        { countryId: 146, countryName: 'Saint Vincent and the Grenadines' },
        { countryId: 147, countryName: 'Samoa' },
        { countryId: 148, countryName: 'San Marino' },
        { countryId: 149, countryName: 'Sao Tome and Principe' },
        { countryId: 150, countryName: 'Saudi Arabia' },
        { countryId: 151, countryName: 'Senegal' },
        { countryId: 152, countryName: 'Serbia' },
        { countryId: 153, countryName: 'Seychelles' },
        { countryId: 154, countryName: 'Sierra Leone' },
        { countryId: 155, countryName: 'Singapore' },
        { countryId: 156, countryName: 'Slovakia' },
        { countryId: 157, countryName: 'Slovenia' },
        { countryId: 158, countryName: 'Solomon Islands' },
        { countryId: 159, countryName: 'Somalia' },
        { countryId: 160, countryName: 'South Africa' },
        { countryId: 161, countryName: 'South Sudan' },
        { countryId: 162, countryName: 'Spain' },
        { countryId: 163, countryName: 'Sri Lanka' },
        { countryId: 164, countryName: 'Sudan' },
        { countryId: 165, countryName: 'Suriname' },
        { countryId: 166, countryName: 'Sweden' },
        { countryId: 167, countryName: 'Switzerland' },
        { countryId: 168, countryName: 'Syria' },
        { countryId: 169, countryName: 'Taiwan' },
        { countryId: 170, countryName: 'Tajikistan' },
        { countryId: 171, countryName: 'Tanzania' },
        { countryId: 172, countryName: 'Thailand' },
        { countryId: 173, countryName: 'Togo' },
        { countryId: 174, countryName: 'Tonga' },
        { countryId: 175, countryName: 'Trinidad and Tobago' },
        { countryId: 176, countryName: 'Tunisia' },
        { countryId: 177, countryName: 'Turkey' },
        { countryId: 178, countryName: 'Turkmenistan' },
        { countryId: 179, countryName: 'Tuvalu' },
        { countryId: 180, countryName: 'Uganda' },
        { countryId: 181, countryName: 'Ukraine' },
        { countryId: 182, countryName: 'United Arab Emirates' },
        { countryId: 183, countryName: 'United Kingdom' },
        { countryId: 184, countryName: 'United States' },
        { countryId: 185, countryName: 'Uruguay' },
        { countryId: 186, countryName: 'Uzbekistan' },
        { countryId: 187, countryName: 'Vanuatu' },
        { countryId: 188, countryName: 'Vatican City' },
        { countryId: 189, countryName: 'Venezuela' },
        { countryId: 190, countryName: 'Vietnam' },
        { countryId: 191, countryName: 'Yemen' },
        { countryId: 192, countryName: 'Zambia' },
        { countryId: 193, countryName: 'Zimbabwe' }
    ];

    // Handle changes to form fields
    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm((prevForm) => ({ ...prevForm, [name]: value }));
    };

    // Handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const payload = {
                ...form,
                countryId: form.countryId // Sending countryId to backend
            };

            // Sending the payload to backend
            const res = await axios.post('http://localhost:8080/api/userInfo/create', payload);
            alert(`User saved! ID: ${res.data.id}`);
        } catch (err) {
            console.error('Submission failed:', err);
            alert('Submission failed. Check console for details.');
        }
    };

    return (
        <div style={{ backgroundColor: '#e6ffe6', padding: '2rem' }}>
            <Box component="form" onSubmit={handleSubmit} sx={{ m: 4, maxWidth: 600 }}>
                <Typography variant="h5" gutterBottom>
                    User Info Form
                </Typography>

                <TextField
                    label="Username"
                    name="userName"
                    value={form.userName}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />

                <TextField
                    label="Password"
                    name="userPassword"
                    type="password"
                    value={form.userPassword}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />

                <TextField
                    label="Date of Birth"
                    name="dateOfBirth"
                    type="date"
                    value={form.dateOfBirth}
                    onChange={handleChange}
                    InputLabelProps={{ shrink: true }}
                    fullWidth
                    margin="normal"
                    required
                />

                {/* Country Dropdown */}
                <TextField
                    select
                    label="Country"
                    name="countryId"
                    value={form.countryId || ''}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                >
                    {countries.map((country) => (
                        <MenuItem key={country.countryId} value={country.countryId}>
                            {country.countryName}
                        </MenuItem>
                    ))}
                </TextField>

                <Button type="submit" variant="contained" sx={{ mt: 2 }}>
                    Submit
                </Button>
            </Box>
        </div>


    );
}
