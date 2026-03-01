import React from "react"
import {BrowserRouter as Router} from "react-router"
import {cleanup, render, screen} from '@testing-library/react'
import '@testing-library/jest-dom'
import UserActions from "./UserActions"

test('renders profile button', () => {
	// given
	cleanup()

	// when
	render(<Router><UserActions/></Router>)

	// then
	const element = screen.getByText(/profile/i)
	expect(element).toBeInTheDocument()
	expect(element.nodeName).toBe('BUTTON')
	expect(element).toHaveClass('page-action')
})

test('renders security button', () => {
	// given`
	cleanup()

	// when
	render(<Router><UserActions/></Router>)

	// then
	const element = screen.getByText(/security/i)
	expect(element).toBeInTheDocument()
	expect(element.nodeName).toBe('BUTTON')
	expect(element).toHaveClass('page-action')
})

test('renders about button', () => {
	// given
	cleanup()

	// when
	render(<Router><UserActions/></Router>)

	// then
	const element = screen.getByText(/about/i)
	expect(element).toBeInTheDocument()
	expect(element.nodeName).toBe('BUTTON')
	expect(element).toHaveClass('page-action')
})

test('renders log out button', () => {
	// given
	cleanup()

	// when
	render(<Router><UserActions/></Router>)

	// then
	const element = screen.getByText(/logout/i)
	expect(element).toBeInTheDocument()
	expect(element.nodeName).toBe('BUTTON')
	expect(element).toHaveClass('page-action')
})

