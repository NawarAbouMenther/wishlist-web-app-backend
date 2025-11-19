const express = require('express');
const cors = require('cors');

const app = express();
const PORT = process.env.PORT || 8080;

app.use(cors());

// GET-Route für Render (Milestone 3)
app.get('/api/wishes', (req, res) => {
    const wishes = [
        'Neuer Laptop',
        'Kopfhörer',
        'Sneaker',
        'Gamer Stuhl'
    ];
    res.json(wishes);
});

// optional: Startseite
app.get('/', (req, res) => {
    res.send('Wishlist Backend läuft 🚀');
});

app.listen(PORT, () => {
    console.log(`Server läuft auf Port ${PORT}`);
});
