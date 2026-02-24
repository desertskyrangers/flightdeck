import {render, screen} from '@testing-library/react';
import CopyrightNotice from './CopyrightNotice';
import React from "react";

test('renders copyright notice', () => {
	render(<CopyrightNotice year={2022}/>);

	const element = screen.getByText(/\u00A9/i);
	expect(element).toBeInTheDocument();
	expect(element).toHaveTextContent('\u00A9 2022 Desert Sky Rangers')
});
