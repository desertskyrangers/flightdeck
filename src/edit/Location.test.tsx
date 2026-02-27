import React, {act} from "react";
import {cleanup, render, screen} from '@testing-library/react';
import '@testing-library/jest-dom';
import {MemoryRouter as Router} from "react-router";
import Location from "./Location";
import Mock from "../util/Mock.tsx";

test('renders name field', () => {
	// given
	cleanup()
	window.fetch = Mock.fetch(()=>{})

	act(() => {
		render(<Router><Location/></Router>);
	})

	// // when
	//
	// // then
	// const element = screen.getByLabelText(/name/i);
	// expect(element).toBeInTheDocument();
	// expect(element).toHaveAttribute('type', 'text');
	// expect(element).toHaveClass('page-field');
});

