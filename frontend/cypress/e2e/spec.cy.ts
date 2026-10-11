const KEYCLOAK_ORIGIN = 'http://localhost:8180'

const login = () => {
  cy.session('admin', () => {
    cy.visit('/')
    cy.origin(KEYCLOAK_ORIGIN, () => {
      cy.get('#username').clear().type('admin')
      cy.get('#password').clear().type('admin')
      cy.get('#kc-login').click()
    })
    cy.contains('button', 'Add Hotel')
  })
}

describe('Hotels E2E', () => {
  beforeEach(() => {
    login()
  })

  it('create, update, view and delete a hotel', () => {
    cy.visit('/')

    // Create
    cy.contains('button', 'Add Hotel').click()
    cy.get('input[name=name]').type('test')
    cy.get('input[name=address]').type('test address')
    cy.get('input[name=latitude]').type('40.4168')
    cy.get('input[name=longitude]').type('-3.7038')
    cy.contains('button', 'Add hotel').click()
    cy.contains('tbody tr', 'test')

    // Update
    cy.contains('tbody tr', 'test').within(() => {
      cy.contains('Edit').click()
    })
    cy.get('input[name=address]').clear().type('updated address')
    cy.contains('button', 'Update').click()
    cy.contains('tbody tr', 'test').should('contain', 'updated address')

    // Detail
    cy.contains('tbody tr', 'test').within(() => {
      cy.contains('Details').click()
    })
    cy.contains('Name')
    cy.contains('Address')
    cy.contains('updated address')
    cy.contains('Back to Hotels List').click()

    // Delete
    cy.contains('tbody tr', 'test').within(() => {
      cy.contains('Delete').click()
    })
    cy.contains('tbody tr', 'updated address').should('not.exist')
  })
})
