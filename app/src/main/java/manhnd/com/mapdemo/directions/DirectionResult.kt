package manhnd.com.mapdemo.directions

import com.google.gson.annotations.SerializedName


/**
 * Created by FRAMGIA\nguyen.duc.manh on 27/12/2017.
 */

data class Directionresult(
        @SerializedName("geocoded_waypoints") val geocodedWaypoints: List<GeocodedWaypoint?>? = listOf(),
        @SerializedName("routes") val routes: List<Route?>? = listOf(),
        @SerializedName("status") val status: String? = "" //OK
)

data class GeocodedWaypoint(
        @SerializedName("geocoder_status") val geocoderStatus: String? = "", //OK
        @SerializedName("place_id") val placeId: String? = "", //ChIJpTvG15DL1IkRd8S0KlBVNTI
        @SerializedName("types") val types: List<String?>? = listOf()
)

data class Route(
        @SerializedName("bounds") val bounds: Bounds? = Bounds(),
        @SerializedName("copyrights") val copyrights: String? = "", //Dữ liệu bản đồ ©2017 Google
        @SerializedName("legs") val legs: List<Leg?>? = listOf(),
        @SerializedName("overview_polyline") val overviewPolyline: OverviewPolyline? = OverviewPolyline(),
        @SerializedName("summary") val summary: String? = "", //ON-401 E
        @SerializedName("warnings") val warnings: List<Any?>? = listOf(),
        @SerializedName("waypoint_order") val waypointOrder: List<Any?>? = listOf()
)

data class Leg(
        @SerializedName("distance") val distance: Distance? = Distance(),
        @SerializedName("duration") val duration: Duration? = Duration(),
        @SerializedName("end_address") val endAddress: String? = "", //Montréal, Québec, Ca-na-đa
        @SerializedName("end_location") val endLocation: EndLocation? = EndLocation(),
        @SerializedName("start_address") val startAddress: String? = "", //Toronto, Ontario, Ca-na-đa
        @SerializedName("start_location") val startLocation: StartLocation? = StartLocation(),
        @SerializedName("steps") val steps: List<Step?>? = listOf(),
        @SerializedName("traffic_speed_entry") val trafficSpeedEntry: List<Any?>? = listOf(),
        @SerializedName("via_waypoint") val viaWaypoint: List<Any?>? = listOf()
)

data class Step(
        @SerializedName("distance") val distance: Distance? = Distance(),
        @SerializedName("duration") val duration: Duration? = Duration(),
        @SerializedName("end_location") val endLocation: EndLocation? = EndLocation(),
        @SerializedName("html_instructions") val htmlInstructions: String? = "", //Đi về hướng <b>Bắc</b> lên <b>Bay St</b> về phía <b>Hagerman St</b>
        @SerializedName("polyline") val polyline: Polyline? = Polyline(),
        @SerializedName("start_location") val startLocation: StartLocation? = StartLocation(),
        @SerializedName("travel_mode") val travelMode: String? = "" //DRIVING
)

data class Duration(
        @SerializedName("text") val text: String? = "", //1 phút
        @SerializedName("value") val value: Int? = 0 //72
)

data class EndLocation(
        @SerializedName("lat") val lat: Double? = 0.0, //43.6557259
        @SerializedName("lng") val lng: Double? = 0.0 //-79.38373369999999
)

data class StartLocation(
        @SerializedName("lat") val lat: Double? = 0.0, //43.6533096
        @SerializedName("lng") val lng: Double? = 0.0 //-79.3827656
)

data class Polyline(
        @SerializedName("points") val points: String? = "" //e`miGhmocNs@Rm@N]JmA^KBcAZSFWHqA`@a@Le@L
)

data class Distance(
        @SerializedName("text") val text: String? = "", //0,3 km
        @SerializedName("value") val value: Int? = 0 //280
)

data class OverviewPolyline(
        @SerializedName("points") val points: String? = "" //e`miGhmocNiI`CeFmHaAmIgDai@mNiw@qJqq@{GyFwBp@ag@fLoVpRcr@vI}d@oIg[ePa`@ax@kC_w@mV}h@af@iPa]y_@mq@xZehFje@{r@jCeDgf@}AgfBs]kiCmrAkuJog@gxAo\o|DoIm_CdX_{A_Gcb@ad@aw@mnB_pDe_D}rIcnBa_NmRuyBvBcmBnAydC_{Bu_Oe@ktBdt@qiBhL}w@eSylD}i@coIi_AcxCm[maDga@__IsiAuiJmbEyzZagBwvMkSypEkT_~C_cAk_H|Y{fB_P{qC}l@q_C{S_i@yA}dAwj@mkB{^}jCbVi|DzUqoAkjA_`Lku@g_Ly\e_Dud@ilDwh@ufBeh@}hBgT{jAkm@wv@mm@s}DcMw`A_z@ifAeU{w@kRuaCieAsrEiw@uvAku@{vDez@_kCgCyqAoB}gBwXkw@om@q}AweBizEamAs`DuSwfA}e@ctC_iAukH}hBqsKc|@_iDowAyrJ_B_v@lY}eBgEajByh@skH{`Dkzc@k]_vCeu@yuCyF_uAgBe_Bsa@mxBaDmfCiTasFmMqcJw]sgRsVaeScNggBxdA_tDdw@gxGr@kuAy[w{@qj@_iCi|@orEyd@o`Aaj@yn@knAsvDmn@msJmm@kvOal@o~Cmf@{cGjJazDcp@w_EgxAw~EcnA_}E^y`E}f@swDoPmt@wn@ofAuc@gtAmu@mvAiu@{{@km@qrAgd@}j@ie@_RuoBwr@svBi`BcmB_kB}sBmzBqc@kz@sTihBjGkpA}@gc@eo@kgBeVuPuoA`C_g@uPol@_|@_dA}`BgeAgd@s^}a@_w@isBidAi~AytAyvBihB_fDmvAojB_zIkaL{t@sdAuWirAgZonAohDuzFyaAkbBon@_k@spAi|@}aHgoMi{EqiJw`Di}H{uDuiMc|EytOevAe~D}|@gQcTiQczBonHesAcrE}~Eo|PwqAalF{_@gmCyAcq@`TqbAzw@atCzLsrAbBehGkFmoAa\}jAc]qqAiCmoBaMajDeSowAsdCyeIy`FmqMsrAigDwi@ir@ch@s~Auz@cvC}vBcvHeSgl@e_@oTq`AcxBkfBsaEwt@u}CizDevJwfA}bDsqA_bBinCgvD{{HwqVwyA_`Ckc@sg@_i@ymAaXkp@mEo`Bej@}_DebA{aAeYay@eiBc|Ho`@mrE}aAueGun@gpDpIy{GaFg~@lB}rEdD_rDv]klBwAqk@aY_t@{lAmwCqbAegBgYoNgi@ye@_iAslBeMa]sIoAwKWcFtMgI{I
)

data class Bounds(
        @SerializedName("northeast") val northeast: Northeast? = Northeast(),
        @SerializedName("southwest") val southwest: Southwest? = Southwest()
)

data class Southwest(
        @SerializedName("lat") val lat: Double? = 0.0, //43.6533096
        @SerializedName("lng") val lng: Double? = 0.0 //-79.3834186
)

data class Northeast(
        @SerializedName("lat") val lat: Double? = 0.0, //45.5017123
        @SerializedName("lng") val lng: Double? = 0.0 //-73.5666069
)