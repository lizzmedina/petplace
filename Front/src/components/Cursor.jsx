import React from 'react'
import AnimatedCursor from "react-animated-cursor"

function Cursor() {
    return (
    <>
        <AnimatedCursor
        innerSize={10}
        //color='142, 209, 185'
        //color = '56, 11, 71'
        color = '53, 194, 219'
        
        outerStyle={{
            border: '3px solid var(--cursor-color)'
        }}
        innerStyle={{
            backgroundColor: 'var(--cursor-color)'
        }}
        clickables={[
            'a',
            'input[type="text"]',
            'input[type="email"]',
            'input[type="number"]',
            'input[type="submit"]',
            'input[type="image"]',
            'label[for]',
            'select',
            'textarea',
            'button',
            '.link'
        ]}
        />
    </>
    
    )
}

export default Cursor