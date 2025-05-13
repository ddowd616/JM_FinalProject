import clickSound from '../assets/NewRecording.m4a';

export const playClickSound = () => {
    const audio = new Audio(clickSound);
    audio.play();
};

