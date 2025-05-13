import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import logoImg from '../assets/TheMoneyMan.png';
import BatmanSound from '../assets/Batman 1966 transition remake 4.mp3';

export default function IntroAnimation() {
    const [started, setStarted] = useState(false);
    const [show, setShow] = useState(true);
    const navigate = useNavigate();

    const handleStart = () => {
        if (started) return;

        setStarted(true);
        const audio = new Audio(BatmanSound);
        audio.play().catch((error) => {
            console.error('Error playing audio:', error);
        });

        // Hide animation and redirect after 12 seconds
        setTimeout(() => {
            setShow(false);
            navigate('/'); // Navigate to home
        }, 6000);
    };

    if (!show) return null;

    return (
        <div
            onClick={handleStart}
            style={{
                cursor: 'pointer',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                height: '100vh',
                backgroundColor: 'black',
                zIndex: 9999,
                position: 'fixed',
                width: '100%',
                top: 0,
                left: 0,
            }}
        >
            {started ? (
                <img src={logoImg} alt="OldJackAttack" className="spin-zoom-out" />
            ) : (
                <div style={{ color: 'white', fontSize: '2rem' }}>Click to Start</div>
            )}
        </div>
    );
}
