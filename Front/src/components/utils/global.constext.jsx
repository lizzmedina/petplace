import { createContext, useContext, useState, useEffect } from "react";

const ContextGlobal = createContext();


const ContextProvider = ({children}) => {

    
    const validationUserUrl = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user/validation/`;
    const sendEmailUrl = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/mail/send/`;
    const urlGetUsers = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user/all`;
    const urlPostUsers = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user`;
    const urlGetCities = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/cities`;
    const urlPostCities = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/cities`; 
    const urlGetProducts = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/petDayCare/all`;
    const urlPostProducts = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/petDayCare`; 
    const urlCategory = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/category`;
    const urlBookingHistory = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/booking/user/`;
    const urlPostBooking = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/booking`;
    const urlEmailBooking = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/mail/send/`
    const urlBookingScore = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/booking/rating`;
    const urlFavorites = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/favorite/list?id=`;
    const urlPostFavorites = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/favorite/?idFavorite=0&userId=`;

    const [selectedCity, setSelectedCity] = useState(null);
    const [selectedDates, setSelectedDates] = useState([null, null]);
    const [recommends, setRecommends] = useState([]);
    const [searchResults, setSearchResults] = useState([]);
    const [title, setTitle] = useState('Recomendaciones');
    const [bookingHistory, setBookingHistory] = useState([{}]);
    const [favorites, setFavorites] = useState([]);
    const [isFavorite, setIsFavorite] = useState(true);
    const [searchFavorites, setSearchFavorites] = useState(false); //  state para busqueda de favoritos
    const [places, setPlaces] = useState([]); // categorias
    const[url, setUrl] = useState(`${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/petDayCare/all`) 
    const [dataCategory, setDataCategory] = useState([])

    const getAllCategories = async()=> {
        const res = await fetch(`${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/category/all`);
        const data = await res.json();
        setPlaces(data)
    }
    //función auxiliar para buscar favoritos por userId:
    const fetchFavorites = async () => {
        const userConnected = JSON.parse(localStorage.getItem("userConnected"));

        if (userConnected) {
            const userId = userConnected.id;
            await fetch(`${urlFavorites}${userId}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            })
                .then((response) => response.json())
                .then((data) => {
                    setFavorites(data);
                })
                .catch((error) => {
                    console.error(error);
                });
        }
    };
    useEffect(() => {
        if(!searchFavorites) {
            fetchFavorites();
            setSearchFavorites (true);
        }
    }, [searchFavorites, setSearchFavorites]);

    useEffect(() => {
        if(places.length === 0){
            getAllCategories();
        }
    }, [places, setPlaces]);

    useEffect(() => {
        if(dataCategory.length === 0) {
            fetch(url)
            .then((res) => res.json())
            .then((data) => {
                setDataCategory(data);
            })
            .catch((error) => {
                error('Error al obtener los datos:', error);
            });
        }
    }, [url, dataCategory, setDataCategory]);


    return (
        <ContextGlobal.Provider 
            value={{
                urlPostFavorites,
                urlFavorites,
                isFavorite,
                setIsFavorite,
                favorites,
                setFavorites,
                urlBookingHistory,
                bookingHistory,
                setBookingHistory,
                searchResults,
                setSearchResults, 
                title,
                setTitle,
                recommends,
                setRecommends, 
                selectedDates, 
                setSelectedDates, 
                selectedCity, 
                setSelectedCity, 
                urlGetProducts, 
                urlPostProducts, 
                urlCategory, 
                urlGetCities,
                urlPostCities,
                urlGetUsers,
                urlBookingScore,
                sendEmailUrl, 
                urlPostUsers, 
                validationUserUrl, 
                getAllCategories, 
                places,
                setPlaces, 
                url, 
                setUrl, 
                dataCategory, 
                setDataCategory,
                urlPostBooking,
                urlEmailBooking
            }}>
            {children}
        </ContextGlobal.Provider>
    )
}

export default ContextProvider;
export const useContextGlobal = () => useContext(ContextGlobal)