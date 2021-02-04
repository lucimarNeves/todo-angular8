const express = require('express');
const path = require('path');
const app = express();


const PORT = process.env.PORT || 4200;

app.use(express.static(path.join(__dirname, '/dist/front-end')));

app.get('/*',(req, res) =>{
    res.sendFile(__dirname + '/dist/front-end/index.html');
});

app.listen(PORT, () =>{
      console.log('Servidor iniciado na porta' + PORT)
})