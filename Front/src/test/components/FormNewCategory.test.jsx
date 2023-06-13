// import {fireEvent, render,  waitFor, screen} from '@testing-library/react';
// import { FormNewCategory } from '../../components/FormNewCategory';


describe('FormNewCategory', () => {
    
    test('should handle input change -title', () => {
        // const { getByLabelText } = render(<FormNewCategory />);

        // const titleInput = getByLabelText('Titulo:');
        // fireEvent.change(titleInput, { target: { value: 'Nuevo título' } });

        // expect(titleInput.value).toBe('Nuevo título');
    });
})
    // test('should handle input change - description', () => {
    //     const { getByLabelText } = render(<FormNewCategory />);
        
    //     const descriptionInput = getByLabelText('Descripción:');
    //     fireEvent.change(descriptionInput, { target: { value: 'Nueva descripción' } });
        
    //     expect(descriptionInput.value).toBe('Nueva descripción');
    // });
        
    // test('should handle input change - image', () => {
    //     const { getByLabelText } = render(<FormNewCategory />);
        
    //     const imageInput = getByLabelText('URL imagen:');
    //     fireEvent.change(imageInput, { target: { value: 'https://example.com/image.jpg' } });
        
    //     expect(imageInput.value).toBe('https://example.com/image.jpg');
    // });

    // test('should submit the form successfully', () => {
    //     const { getByLabelText } = render(<FormNewCategory />);

    //     fireEvent.change(getByLabelText('Titulo:'), { target: { value: 'Nuevo título' } });
    //     expect(getByLabelText('Titulo:').value).toBe('Nuevo título');
            
    //     fireEvent.change(getByLabelText('Descripción:'), { target: { value: 'Nueva descripción' } });
    //     expect(getByLabelText('Descripción:').value).toBe('Nueva descripción');
            
    //     fireEvent.change(getByLabelText('URL imagen:'), { target: { value: 'https://example.com/image.png' } });
    //     expect(getByLabelText('URL imagen:').value).toBe('https://example.com/image.png');
    // });
    
    // test('should update state on field value change', () => {
    //     const { getByLabelText } = render(<FormNewCategory />);

    //     fireEvent.change(getByLabelText('Descripción:'), { target: { value: 'Nueva descripción' } });

    //     expect(getByLabelText('Descripción:').value).toBe('Nueva descripción');
    // });

