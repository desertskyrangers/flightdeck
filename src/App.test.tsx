import React from 'react';
import {cleanup, render, screen} from '@testing-library/react';
import '@testing-library/jest-dom';
import Mock from "./util/Mock.tsx";
import {App} from './App';


test('renders application', () => {
  // given
  cleanup()
  window.fetch = Mock.fetch({});

  // when
  render(<App/>)

  // then
  const element = screen.getByText(/Page/i)
  expect(element).toBeInTheDocument()
  expect(element).toHaveTextContent('Page Not Found')
});
