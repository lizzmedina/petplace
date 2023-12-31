
import { CardRecomends } from "../components/CardRecomends";
import { useContextGlobal } from "../components/utils/global.constext";

export const Favorites = () => {
    const { favorites} = useContextGlobal();
    
    return (
        <div className="space-section favorites-container">
            <h2 className="title-favorites">Favorites</h2>
            
            {favorites.length === 0 ? (
                <h3 className="h3-no-favorites">Aún no se han agregado hospedajes a favoritos.</h3>
            ) : (
                <div className="render-cards-recommends">
                    {favorites.map((favorite) => {
                        const petDayCare = favorite.petDayCare;
                        return (
                            <CardRecomends
                                key={petDayCare?.id}
                                number={petDayCare?.id}
                                type={petDayCare?.type.title}
                                name={petDayCare?.name}
                                image={petDayCare?.images}
                                capacity={petDayCare?.capacity}
                                rating={petDayCare?.average}
                                city={petDayCare?.city.name}
                                address={petDayCare?.address}
                                detail={petDayCare?.detail}
                                basicPrice={petDayCare?.basicPrice}
                                characteristics={petDayCare?.characteristics}
                            />
                        );
                    })}
                </div>
            )}
        </div>
    );
};