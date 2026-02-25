import React from 'react';
import {render, screen, cleanup} from '@testing-library/react';
import {App} from './App';
import '@testing-library/jest-dom';

test('renders application', () => {
  // given
  cleanup()

  // when
  render(<App/>)

  // then
  const element = screen.getByText(/Parcel/i)
  expect(element).toBeInTheDocument()
  expect(element).toHaveTextContent('FlightDeck Parcel React App')
});
