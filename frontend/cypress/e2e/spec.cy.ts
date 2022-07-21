describe('My First Test', () => {
  
  it('Visits Hotels App', () => {
    cy.visit('http://localhost')

    cy.contains('Hotel Details')
  })

  it('Opens the add Hotel form', () => {
    cy.contains('Add Hotel').click()

    cy.url().should('include', '/add-hotel')
  })

  it('Creates one Hotel', () => {
    cy.get('input[name=name]').type("test")
    cy.get('input[name=description]').type("test description")
    cy.get('input[name=price]').type("55.99")

    cy.contains('Update').click()
    cy.contains('test')
  })

  it('Updates new test Hotel', () => {
    cy.get('tbody>tr>td').last()
      .contains('Edit').click()
    cy.get('input[name=price]').clear().type("100.00")
    cy.contains('Update').click()

    cy.get('tbody>tr>td').contains("€100.00")
  })

  it('Show new test Hotel detail', () => {
    cy.get('tbody>tr>td').last()
      .contains('Detail').click()
    
    // Contains expected details
    cy.contains('Name')
    cy.contains('Description')
    cy.contains('Mean Room Price')

    cy.contains('Back to Hotels List').click()
  })

  it('Delete dew test Hotel', () => {
    cy.get('tbody>tr>td').last()
      .contains('Delete').click()
  })
})