const neDB = require('../configurations/database')
const api = {}

api.findAll = (request, response) => {
  neDB.find({}).exec((err, allCards) => {
    if(err) {
      return response.status(500).json(err);
    } else {
      if(allCards.length == 0) {
        return response.status(204).send("Nenhum cartão encontrado!");
      } else {
        return response.status(201).json(allCards);
      }
    }
  });
}

api.save = (request, response) => {
  const {cardNumber, embossName, customerName, 
    documentNumber, motherName, address, city} = request.body;


  if(!cardNumber || cardNumber < 0 ) {
    return response.status(404).send("Número do cartão inválido!");
  }

  if(!embossName || !embossName.length) {
    return response.status(404).send("Nome em relevo inválido!");
  }

  if(!customerName || !customerName.length) {
    return response.status(404).send("Nome do cliente inválido!");
  }

  if(!documentNumber || documentNumber < 0 ) {
    return response.status(404).send("Número do documento inválido!");
  }

  if(!motherName || !motherName.length) {
    return response.status(404).send("Nome da mãe inválido!");
  }

  if(!address || !address.length) {
    return response.status(404).send("Endereço inválido!");
  }

  if(!city || !city.length) {
    return response.status(404).send("Cidade inválida!");
  }

  var card = {
    cardNumber : cardNumber,
    embossName : embossName,
    customerName : customerName,
    documentNumber : documentNumber,
    motherName : motherName,
    address : address,
    city : city
  };

  neDB.insert(card, (err, newCard) => {
    if(err) {
      return response.status(500).json(err);
    } else {
      return response.status(201).json(newCard);
    }
  });
}

api.update = (request, response) => {
}

api.delete = (request, response) => {
  const id = request.params.id;

  if(!id || !id.length) {
    return response.status(404).send("Id inválido");
  }

  neDB.remove({ _id: id }, {}, (err, numRemoved) => {
    if(err) {
      return response.status(500).json(err);
    } else {
      if(numRemoved == 0) {
        return response.status(404).send("Cartão não encontrado!");
      } else {
        return response.status(200).send("Removido com sucesso!");
      }
    }
  });
}

api.findById = (request, response) => {
  const id = request.params.id;

  if(!id || !id.length) {
    return response.status(404).send("Id inválido");
  }

  neDB.findOne({_id: id}, (err, card) => {
    if(err) {
      return response.status(500).json(err);
    } else {
      if (card == null) {
        return response.status(404).send("Cartão não encontrado!");
      } else {
        return response.status(200).json(card);
      }
    }
  });
}

api.getAllCard = (request, response) => {
}

module.exports = api