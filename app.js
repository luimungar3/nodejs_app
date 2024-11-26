const express = require('express');
const app = express();

app.get('/', (req, res) => {
    res.send('prueba aplicación Node.js');
});

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Server is running at http://localhost:${PORT}`);
});
