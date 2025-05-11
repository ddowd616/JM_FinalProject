import { Container, Typography } from '@mui/material';
import UserInfoForm from './components/UserInfoForm';

function App() {
    return (
        <Container>
            <Typography variant="h4" sx={{ mt: 4 }}>User Info Form</Typography>
            <UserInfoForm />
        </Container>
    );
}

export default App;