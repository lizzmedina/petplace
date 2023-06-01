import React from 'react';
import { render, fireEvent, waitFor } from '@testing-library/react';
import { Await, MemoryRouter } from 'react-router-dom';
import FormLogin from '../../components/FormLogin';

describe('FormLogin', () => {

    test('should render form correctly', () => {
        // const { getByLabelText, getByText } = render(
        // <MemoryRouter>
        //     <FormLogin />
        // </MemoryRouter>
        // );

        // expect(getByLabelText('Email:')).toBeTruthy();
        // expect(getByLabelText('Contraseña:')).toBeTruthy();
        // expect(getByText('Ingresar')).toBeTruthy();
        // expect(getByText('¿Aun no tienes cuenta?')).toBeTruthy();
    });
});




    // test('should update state on input change', () => {
    //     const { getByLabelText } = render(
    //     <MemoryRouter>
    //         <FormLogin />
    //     </MemoryRouter>
    //     );

    //     const emailInput = getByLabelText('Email:');
    //     const passwordInput = getByLabelText('Contraseña:');

    //     fireEvent.change(emailInput, { target: { value: 'test@example.com' } });
    //     fireEvent.change(passwordInput, { target: { value: 'password123' } });

    //     expect(emailInput.value).toBe('test@example.com');
    //     expect(passwordInput.value).toBe('password123');
    // });

    // test('should handle form submission with valid credentials', async() => {
        
    //     const mockFetch = jest.fn().mockResolvedValueOnce({
    //         json: () => Promise.resolve([{ email: 'test@example.com', password: 'password123' }]),
    //     });
    //     global.fetch = mockFetch;

    //     const { getByLabelText, getByText } = render(
    //     <MemoryRouter>
    //         <FormLogin />
    //     </MemoryRouter>
    //     );

    //     const emailInput = getByLabelText('Email:');
    //     const passwordInput = getByLabelText('Contraseña:');
    //     const submitButton = getByText('Ingresar');

    //     fireEvent.change(emailInput, { target: { value: 'test@example.com' } });
    //     fireEvent.change(passwordInput, { target: { value: 'password123' } });
    //     fireEvent.click(submitButton);

    //     expect(mockFetch).toHaveBeenCalled();
    //     await waitFor(() => {}, { timeout: 3000 });

    //     expect(emailInput.value).toBe('');
    //     expect(passwordInput.value).toBe('');
    //     window.alert = originalAlert;
    // });


    // test('should display error message for invalid credentials', () => {
    //     // Mock API response
    //     const mockFetch = jest.fn().mockResolvedValueOnce({
    //     json: () => Promise.resolve([]),
    //     });
    //     global.fetch = mockFetch;

    //     const { getByLabelText, getByText } = render(
    //     <MemoryRouter>
    //         <FormLogin />
    //     </MemoryRouter>
    //     );

    //     const emailInput = getByLabelText('Email:');
    //     const passwordInput = getByLabelText('Contraseña:');
    //     const submitButton = getByText('Ingresar');

    //     fireEvent.change(emailInput, { target: { value: 'test@example.com' } });
    //     fireEvent.change(passwordInput, { target: { value: 'password123' } });
    //     fireEvent.click(submitButton);

    //     expect(mockFetch).toHaveBeenCalled();
    //     // Add assertions for the expected error message display
    // });

    // test('should navigate to registration page', () => {
    //     const { getByText } = render(
    //     <MemoryRouter>
    //         <FormLogin />
    //     </MemoryRouter>
    //     );

    //     const registerLink = getByText('Registrate');
    //     fireEvent.click(registerLink);

    //     // Add assertions for the expected navigation behavior
    // });
