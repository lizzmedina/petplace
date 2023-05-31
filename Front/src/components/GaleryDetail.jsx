import { useNavigate } from 'react-router-dom'





export const GaleryDetail = ({ image }) => {

    const navigate = useNavigate()

    if (!image) {
        return image;
    }


    return (

        <div  className="">

            <button type="button" class="btn" data-toggle="modal" data-target="#myModal" data-local="#myCarousel">Demo</button>

            <div id="myCarousel" class="carousel slide carousel-fit" data-ride="carousel">
               
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>

                
                <div class="carousel-inner">
                    <div class="item active">
                        <img data-src="{image[0]}" alt="First slide"/>
                            <div class="carousel-caption">
                                <h3>First slide label</h3>
                                <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                            </div>
                    </div>
                    <div class="item">
                        <img data-src="{image[1]}" alt="Second slide"/>
                            <div class="carousel-caption">
                                <h3>Second slide label</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            </div>
                    </div>
                    <div class="item">
                        <img data-src="{image[2]}" alt="Third slide"/>
                            <div class="carousel-caption">
                                <h3>Third slide label</h3>
                                <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                            </div>
                    </div>
                    <div class="item">
                        <img data-src="{image[3]}" alt="Third slide"/>
                            <div class="carousel-caption">
                                <h3>Third slide label</h3>
                                <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                            </div>
                    </div>
                    <div class="item">
                        <img data-src="{image[4]}" alt="Third slide"/>
                            <div class="carousel-caption">
                                <h3>Third slide label</h3>
                                <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                            </div>
                    </div>
                </div>

                
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
            </div>

            <div class="modal fade modal-fullscreen force-fullscreen" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Modal title</h4>
                        </div>
                        <div class="modal-body">
                            <p>One fine body…</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )

}




