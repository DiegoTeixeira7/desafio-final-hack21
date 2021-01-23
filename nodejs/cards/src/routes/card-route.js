const api = require('../controllers/card-controller')

module.exports = (app) => {
    app.route('/cards')
        .get(api.findAll)
        .post(api.save)
        .get(api.getAllCards)
    
    app.route('/cards/:id')
        .put(api.update)
        .delete(api.delete)
        .get(api.findById)
}