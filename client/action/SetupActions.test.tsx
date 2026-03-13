import {BrowserRouter as Router} from "react-router";
import {cleanup, render, screen} from '@testing-library/react';
import '@testing-library/jest-dom';
import SetupActions from "./SetupActions";
import React from "react";

test('renders aircraft button', () => {
	// given
	cleanup()

	// when
	render(<Router><SetupActions/></Router>)

	// then
	const element = screen.getByText(/aircraft/i)
	expect(element).toBeInTheDocument()
	expect(element.nodeName).toBe('BUTTON')
	expect(element).toHaveClass('page-action')
})

test('renders batteries button', () => {
	// given
	cleanup()

	// when
	render(<Router><SetupActions/></Router>)

	// then
	const element = screen.getByText(/batteries/i)
	expect(element).toBeInTheDocument()
	expect(element.nodeName).toBe('BUTTON')
	expect(element).toHaveClass('page-action')
})

// test('renders groups button', () => {
//  // given
//  cleanup()
//
//  // when
// 	render(<Router><SetupActions/></Router>)
//
//  // then
// 	const element = screen.getByText(/groups/i)
// 	expect(element).toBeInTheDocument()
// 	expect(element.nodeName).toBe('BUTTON')
// 	expect(element).toHaveClass('page-action')
// })
