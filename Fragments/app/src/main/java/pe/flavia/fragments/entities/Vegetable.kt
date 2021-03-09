package pe.flavia.fragments.entities

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import pe.flavia.fragments.utils.intoCurrency

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/7/21 - 8:54 PM
    Solera Mobile
*/

@Parcelize
class Vegetable(val id: Int = -1, val name: String = "", val price: Double = 0.0, val description: String = "", val img: String = "") : Parcelable {
    @IgnoredOnParcel
    val priceText = this.price.intoCurrency
    @IgnoredOnParcel
    var isSelected = false
}

enum class EVegetables(val id: Int, val vName: String, val price: Double, val description: String, val img: String){
    TOMATOE(1, "Tomate", 2.00, "Solanum lycopersicum, cuyo fruto es el tomate, conocida comúnmente como tomatera, es una especie de planta herbácea del género Solanum de la familia Solanaceae; es nativa de América Central y del norte y noroeste de Sudamérica; su uso como comida se habría originado en Sudamérica hace 2600 años. El nombre proviene de la palabra náhuatl xītomatl.\n\nLa planta es cultivada en el mundo entero para el consumo de su fruto, el tomate, tanto fresco como procesado de diferentes maneras: salsa, puré, zumo, deshidratado, enlatado, etcétera.\n\nTambién hay una especie de anémona llamada Tomate de Mar.", "img_tomatoe"),
    ORANGE(2, "Naranja", 3.00, "La naranja es una fruta cítrica obtenida del naranjo dulce (Citrus × sinensis), del naranjo amargo (Citrus × aurantium) y de naranjos de otras variedades o híbridos, de origen asiático. Es un hesperidio carnoso de cáscara más o menos gruesa y endurecida, y su pulpa está formada típicamente por once gajos u hollejos llenos de jugo, el cual contiene mucha vitamina C, flavonoides y aceites esenciales. Se cultiva como un antiguo árbol ornamental y para obtener fragancias de sus frutos. Es más pequeña y dulce que el pomelo o toronja y más grande, aunque menos perfumada que la mandarina. Existen numerosas variedades de naranjas, siendo la mayoría híbridos producidos a partir de las especies Citrus maxima (pamplemusa), Citrus reticulata (mandarina) y Citrus medica (cidro).\n\nSegún la FAO, en 2014 la fruticultura mundial produjo unos 71 millones de toneladas de este cítrico, una cuarta parte proveniente de Brasil y el resto de China, India, México, EE. UU., España, Egipto, Indonesia, Turquía y otros países.", "img_orange"),
    STRAWBERRY(3, "Fresa", 14.00, "Fragaria, llamado comúnmente fresa o frutilla, es un género de plantas rastreras estoloníferas de la familia Rosaceae. Agrupa unos 400 taxones descritos, de los cuales solo unos 20 están aceptados. Son cultivadas por su fruto comestible (eterio) llamado de la misma manera, fresa o frutilla. Las variedades cultivadas comercialmente son por lo general híbridos, en especial Fragaria × ananassa, que ha reemplazado casi universalmente a las especies silvestres locales, como la eurasiática Fragaria vesca, por el superior tamaño de sus frutos.", "img_strawberry"),
    POTATOE(4, "Papa", 1.80, "Solanum tuberosum, de nombre común papa o patata, es una especie herbácea perteneciente al género Solanum de la familia de las solanáceas, originaria de la región que comprende el altiplano sur del Perú y el noroccidente de Bolivia. Fue domesticada en el altiplano andino y en las cercanías del lago Titicaca por los habitantes de esta región desde hace unos 8000 años. En el siglo XVI comenzó a ser trasladada a Europa por los conquistadores españoles quienes la consideraban una curiosidad botánica y no una planta alimenticia. Su consumo fue creciendo, aunque al principio como planta forrajera y de jardín por sus flores; su uso gastronómico se expandió a todo el mundo desde el siglo XVIII gracias a los escritos agronómicos del francés Antoine Parmentier y del irlandés afincado en España Enrique Doyle, hasta convertirse en uno de los principales alimentos del ser humano.", "img_potatoe"),
    ONION(5, "Cebolla", 2.30, "Allium cepa, comúnmente conocida como cebolla, es una planta herbácea bienal perteneciente a la familia de las amarilidáceas. Es la especie más cultivada del género Allium, el cual contiene varias especies que se denominan «cebollas» y que se cultivan como alimento. Ejemplos de las mismas son la cebolla de verdeo (Allium fistulosum), la cebolla escalonia (Allium ascalonicum) y la cebolla de hoja o ciboulette (Allium schoenoprasum).", "img_onion");

    companion object{
        fun getVegetablesList(): MutableList<Vegetable> {
            val newList = values().map { Vegetable(it.id, it.vName, it.price, it.description, it.img) }.toMutableList()
            newList.forEach {
                it.isSelected = newList.indexOf(it) == 0
            }
            return newList
        }
    }
}