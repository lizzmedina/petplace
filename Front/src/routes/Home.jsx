import { Categories } from "../routes/Categories";
import { Recommends } from "../routes/Recommends";
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
