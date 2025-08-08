describe('Hotels E2E', () => {

  it('create, update, view and delete a hotel', () => {
    cy.visit('/')

    // Create
    cy.contains('button', 'Add Hotel').click()
    cy.get('input[name=name]').type('test')
    cy.get('input[name=description]').type('test description')
    cy.get('input[name=price]').type('55.99')
    cy.contains('button', 'Update').click()
    cy.contains('test')

    // Update
    cy.contains('tbody tr', 'test').within(() => {
      cy.contains('Edit').click()
    })
    cy.get('input[name=price]').clear().type('100.00')
    cy.contains('button', 'Update').click()
    cy.contains('tbody tr', 'test').should('contain', '€100.00')

    // Detail
    cy.contains('tbody tr', 'test').within(() => {
      cy.contains('Detail').click()
    })
    cy.contains('Name')
    cy.contains('Description')
    cy.contains('Mean Room Price')
    cy.contains('Back to Hotels List').click()

    // Delete
    cy.contains('tbody tr', 'test').within(() => {
      cy.contains('Delete').click()
    })
  })
})