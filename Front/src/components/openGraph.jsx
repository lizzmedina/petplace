import React from 'react'



const openGraph = () => {
  return (
    <div>
        {/* <!-- Primary Meta Tags --> */}
    <title>PetPlace Lo mejor para tu mascota!!</title>
    <meta name="title" content="PetPlace Lo mejor para tu mascota!!" />
    <meta name="description"
      content="Hey!! revisa este excelente lugar de petPlace para que puedas dejar tu mascota a salvo y puedas viajar tranquilo" />
  
    {/* <!-- Open Graph / Facebook --> */}
    <meta property="og:type" content="website" />
    <meta property="og:url" content="http://localhost:5173/" />
    <meta property="og:title" content="PetPlace Lo mejor para tu mascota!!" />
    <meta property="og:description" content="Hey!! revisa este excelente lugar de petPlace para que puedas dejar tu mascota a salvo y puedas viajar tranquilo" />
    <meta property="og:image" content="/Detail/:id/{image[0]}" />
  
   {/*  <!-- Twitter --> */}
    <meta property="twitter:card" content="summary_large_image" />
    <meta property="twitter:url" content="http://localhost:5173/" />
    <meta property="twitter:title" content="PetPlace Lo mejor para tu mascota!!" />
    <meta property="twitter:description"
      content="Hey!! revisa este excelente lugar de petPlace para que puedas dejar tu mascota a salvo y puedas viajar tranquilo" />
    <meta property="twitter:image" content="https://metatags.io/images/meta-tags.png" />
  
    {/* <!-- Meta Tags Generated with https://metatags.io --> */}
    </div>
  )
}

export default openGraph