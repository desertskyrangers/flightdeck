import {cleanup, render, screen} from '@testing-library/react'
import App from './App'

test('renders application', () => {
	// given
	cleanup()

	// when
	render(<App/>)

	// then
	const element = screen.getByText(/Page/i)
	expect(element).toBeInTheDocument()
	expect(element).toHaveTextContent('Page Not Found')
})

