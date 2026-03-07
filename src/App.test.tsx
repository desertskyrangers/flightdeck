import {cleanup, render, screen} from '@testing-library/react'
import {App} from './App'

test('renders application', () => {
	// given
	cleanup()

	// when
	render(<App/>)

	// then
	const element = screen.getByText(/Username/i)
	expect(element).toBeInTheDocument()
	expect(element).toHaveTextContent('Username or email address')
})

