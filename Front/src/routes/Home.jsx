import { Categories } from "../components/Categories";
import { Recommends } from "../components/Recommends";
import { Searcher } from "../components/Searcher";

export const Home = () => {
  return (
    <div className="home-container">
    <Searcher/>
    <Categories/>
    <Recommends/>
    </div>
  )
}
