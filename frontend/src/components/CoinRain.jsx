import React from 'react';
import '../CoinRain.css';

export default function CoinRain() {
    const coins = Array.from({ length: 500 }, (_, index) => {
        const left = Math.random() * 100;
        const delay = Math.random() * 2;
        const duration = 2 + Math.random() * 2;

        return (
            <div
                key={index}
                className="coin"
                style={{
                    left: `${left}%`,
                    animationDelay: `${delay}s`,
                    animationDuration: `${duration}s`
                }}
            />
        );
    });

    return <div className="coin-rain-container">{coins}</div>;
}
