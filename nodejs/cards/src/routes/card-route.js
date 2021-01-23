const api = require('../controllers/card-controller')

module.exports = (app) => {
    app.route('/cards')
        .get(api.findAll)
        .post(api.save)
    
    app.route('/cards/:id')
        .put(api.update)
        .delete(api.delete)
        .get(api.findById)
    
    app.route('/cards/paginationAndSorting')
        .get(api.getAllCard)

}