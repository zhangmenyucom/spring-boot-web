package com.taylor.common;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 1:28
 */
public class Constants {

    /***查询股票基本信息**/
    public static final String METHOD_URL_STOCK_BASE_INFO = "https://gupiao.baidu.com/api/rails/stockbasicbatch";

    /***查询股票分时信息**/
    public static final String METHOD_URL_STOCK_TIME_INFO = "https://gupiao.baidu.com/api/stocks/stocktimeline";

    /***查询股票日K信息**/
    public static final String METHOD_URL_STOCK_DAY_INFO = "https://gupiao.baidu.com/api/stocks/stockdaybar";

    /***查询股票周K信息**/
    public static final String METHOD_URL_STOCK_WEEK_INFO = "https://gupiao.baidu.com/api/stocks/stockweekbar";

    /***查询股票月K信息**/
    public static final String METHOD_URL_STOCK_MONTH_INFO = "https://gupiao.baidu.com/api/stocks/stockmonthbar";

    /***查询股票资金流入情况信息**/
    public static final String METHOD_URL_STOCK_FOUNDS_IN_OUT = "https://gupiao.baidu.com/api/stocks/stockfunds";

    public static final Double CURRENT_PRICE_LIMIT = 30.0;


    public static final String STOCK_CODE_SH = "SH600000,SH600004,SH600006,SH600007,SH600008,SH600009,SH600010,SH600011,SH600012,SH600015,SH600016,SH600017,SH600018,SH600019,SH600020,SH600021,SH600022,SH600023,SH600025,SH600026,SH600027,SH600028,SH600029,SH600030,SH600031,SH600033,SH600035,SH600036,SH600037,SH600038,SH600039,SH600048,SH600050,SH600051,SH600052,SH600053,SH600054,SH600055,SH600056,SH600057,SH600058,SH600059,SH600060,SH600061,SH600062,SH600063,SH600064,SH600066,SH600067,SH600068,SH600069,SH600070,SH600071,SH600072,SH600073,SH600075,SH600076,SH600077,SH600078,SH600079,SH600080,SH600081,SH600082,SH600083,SH600084,SH600085,SH600086,SH600088,SH600089,SH600090,SH600093,SH600094,SH600095,SH600096,SH600097,SH600098,SH600099,SH600100,SH600101,SH600103,SH600104,SH600105,SH600106,SH600107,SH600108,SH600109,SH600110,SH600111,SH600113,SH600114,SH600115,SH600116,SH600117,SH600118,SH600119,SH600120,SH600122,SH600123,SH600125,SH600126,SH600127,SH600128,SH600129,SH600130,SH600131,SH600132,SH600133,SH600135,SH600136,SH600137,SH600138,SH600139,SH600141,SH600143,SH600146,SH600148,SH600150,SH600151,SH600152,SH600153,SH600155,SH600156,SH600157,SH600158,SH600159,SH600160,SH600161,SH600162,SH600163,SH600165,SH600166,SH600167,SH600168,SH600169,SH600170,SH600171,SH600172,SH600173,SH600175,SH600176,SH600177,SH600178,SH600179,SH600180,SH600182,SH600183,SH600184,SH600185,SH600186,SH600187,SH600188,SH600189,SH600190,SH600191,SH600192,SH600193,SH600195,SH600196,SH600197,SH600198,SH600199,SH600200,SH600201,SH600202,SH600203,SH600206,SH600207,SH600208,SH600209,SH600210,SH600211,SH600212,SH600213,SH600215,SH600216,SH600217,SH600218,SH600219,SH600220,SH600221,SH600222,SH600223,SH600226,SH600227,SH600229,SH600230,SH600231,SH600232,SH600233,SH600235,SH600236,SH600237,SH600238,SH600239,SH600240,SH600241,SH600242,SH600243,SH600246,SH600248,SH600249,SH600250,SH600251,SH600252,SH600255,SH600256,SH600257,SH600258,SH600259,SH600260,SH600261,SH600262,SH600266,SH600267,SH600268,SH600269,SH600270,SH600271,SH600272,SH600273,SH600276,SH600277,SH600278,SH600279,SH600280,SH600281,SH600282,SH600283,SH600284,SH600285,SH600287,SH600288,SH600290,SH600291,SH600292,SH600293,SH600295,SH600297,SH600298,SH600299,SH600300,SH600302,SH600303,SH600305,SH600306,SH600307,SH600308,SH600309,SH600310,SH600311,SH600312,SH600313,SH600315,SH600316,SH600317,SH600318,SH600319,SH600320,SH600321,SH600322,SH600323,SH600325,SH600326,SH600327,SH600328,SH600329,SH600330,SH600331,SH600332,SH600333,SH600335,SH600336,SH600337,SH600338,SH600339,SH600340,SH600343,SH600345,SH600346,SH600348,SH600350,SH600351,SH600352,SH600353,SH600354,SH600355,SH600356,SH600358,SH600359,SH600360,SH600361,SH600362,SH600363,SH600365,SH600366,SH600367,SH600368,SH600369,SH600370,SH600371,SH600372,SH600373,SH600375,SH600376,SH600377,SH600378,SH600379,SH600380,SH600381,SH600382,SH600383,SH600385,SH600386,SH600387,SH600388,SH600389,SH600390,SH600391,SH600392,SH600393,SH600395,SH600396,SH600397,SH600398,SH600399,SH600400,SH600405,SH600406,SH600408,SH600409,SH600410,SH600415,SH600416,SH600418,SH600419,SH600420,SH600421,SH600422,SH600426,SH600428,SH600429,SH600433,SH600435,SH600436,SH600438,SH600439,SH600444,SH600446,SH600448,SH600449,SH600452,SH600455,SH600456,SH600458,SH600459,SH600460,SH600461,SH600462,SH600463,SH600466,SH600467,SH600468,SH600469,SH600470,SH600475,SH600476,SH600477,SH600478,SH600479,SH600480,SH600481,SH600482,SH600483,SH600485,SH600486,SH600487,SH600488,SH600489,SH600490,SH600491,SH600493,SH600495,SH600496,SH600497,SH600498,SH600499,SH600500,SH600501,SH600502,SH600503,SH600505,SH600506,SH600507,SH600508,SH600509,SH600510,SH600511,SH600512,SH600513,SH600515,SH600516,SH600517,SH600518,SH600519,SH600520,SH600521,SH600522,SH600523,SH600525,SH600526,SH600527,SH600528,SH600529,SH600530,SH600531,SH600532,SH600533,SH600535,SH600536,SH600537,SH600538,SH600539,SH600543,SH600545,SH600546,SH600547,SH600548,SH600549,SH600550,SH600551,SH600552,SH600555,SH600557,SH600558,SH600559,SH600560,SH600561,SH600562,SH600563,SH600565,SH600566,SH600567,SH600568,SH600569,SH600570,SH600571,SH600572,SH600573,SH600575,SH600576,SH600577,SH600578,SH600579,SH600580,SH600581,SH600582,SH600583,SH600584,SH600585,SH600586,SH600587,SH600588,SH600589,SH600590,SH600592,SH600593,SH600594,SH600595,SH600596,SH600597,SH600598,SH600599,SH600600,SH600601,SH600602,SH600603,SH600604,SH600605,SH600606,SH600609,SH600610,SH600611,SH600612,SH600613,SH600614,SH600615,SH600616,SH600617,SH600618,SH600619,SH600620,SH600621,SH600622,SH600623,SH600624,SH600626,SH600628,SH600629,SH600630,SH600633,SH600634,SH600635,SH600637,SH600638,SH600639,SH600640,SH600641,SH600642,SH600643,SH600644,SH600645,SH600647,SH600648,SH600649,SH600650,SH600651,SH600652,SH600653,SH600655,SH600657,SH600658,SH600660,SH600661,SH600662,SH600663,SH600664,SH600665,SH600666,SH600667,SH600668,SH600671,SH600673,SH600674,SH600675,SH600676,SH600677,SH600678,SH600679,SH600681,SH600682,SH600683,SH600684,SH600685,SH600686,SH600687,SH600688,SH600689,SH600690,SH600691,SH600692,SH600693,SH600694,SH600695,SH600697,SH600698,SH600699,SH600701,SH600702,SH600703,SH600704,SH600705,SH600706,SH600707,SH600708,SH600711,SH600712,SH600713,SH600714,SH600715,SH600716,SH600717,SH600718,SH600719,SH600720,SH600721,SH600722,SH600723,SH600724,SH600726,SH600727,SH600728,SH600729,SH600730,SH600731,SH600734,SH600735,SH600736,SH600737,SH600738,SH600739,SH600740,SH600741,SH600742,SH600743,SH600744,SH600745,SH600746,SH600748,SH600749,SH600750,SH600751,SH600753,SH600754,SH600755,SH600756,SH600757,SH600758,SH600759,SH600760,SH600761,SH600763,SH600764,SH600765,SH600766,SH600768,SH600769,SH600770,SH600771,SH600773,SH600774,SH600775,SH600776,SH600777,SH600778,SH600779,SH600780,SH600781,SH600782,SH600783,SH600784,SH600785,SH600787,SH600789,SH600790,SH600791,SH600792,SH600793,SH600794,SH600795,SH600796,SH600797,SH600798,SH600800,SH600801,SH600802,SH600803,SH600804,SH600805,SH600807,SH600808,SH600809,SH600810,SH600811,SH600812,SH600814,SH600816,SH600818,SH600819,SH600820,SH600821,SH600822,SH600823,SH600824,SH600825,SH600826,SH600827,SH600828,SH600829,SH600830,SH600831,SH600833,SH600834,SH600835,SH600836,SH600837,SH600838,SH600839,SH600841,SH600843,SH600845,SH600846,SH600848,SH600850,SH600851,SH600853,SH600854,SH600855,SH600856,SH600857,SH600858,SH600859,SH600861,SH600862,SH600863,SH600864,SH600865,SH600866,SH600867,SH600868,SH600869,SH600870,SH600871,SH600872,SH600873,SH600874,SH600875,SH600876,SH600879,SH600880,SH600881,SH600882,SH600883,SH600884,SH600885,SH600886,SH600887,SH600888,SH600889,SH600890,SH600891,SH600892,SH600893,SH600894,SH600895,SH600896,SH600897,SH600898,SH600900,SH600903,SH600908,SH600909,SH600917,SH600919,SH600926,SH600933,SH600936,SH600939,SH600958,SH600959,SH600960,SH600961,SH600962,SH600963,SH600965,SH600966,SH600967,SH600969,SH600970,SH600971,SH600973,SH600975,SH600976,SH600977,SH600978,SH600979,SH600980,SH600981,SH600982,SH600983,SH600984,SH600985,SH600986,SH600987,SH600988,SH600990,SH600992,SH600993,SH600995,SH600996,SH600997,SH600998,SH600999,SH601000,SH601001,SH601002,SH601003,SH601006,SH601007,SH601008,SH601009,SH601010,SH601011,SH601012,SH601015,SH601016,SH601018,SH601019,SH601020,SH601021,SH601028,SH601038,SH601058,SH601069,SH601086,SH601088,SH601098,SH601099,SH601100,SH601101,SH601107,SH601108,SH601111,SH601113,SH601116,SH601117,SH601118,SH601126,SH601127,SH601128,SH601137,SH601139,SH601155,SH601158,SH601163,SH601166,SH601168,SH601169,SH601177,SH601179,SH601186,SH601188,SH601198,SH601199,SH601200,SH601206,SH601208,SH601211,SH601212,SH601216,SH601218,SH601222,SH601225,SH601226,SH601228,SH601229,SH601231,SH601233,SH601238,SH601258,SH601288,SH601311,SH601313,SH601318,SH601326,SH601328,SH601333,SH601336,SH601339,SH601366,SH601368,SH601369,SH601375,SH601377,SH601388,SH601390,SH601398,SH601500,SH601515,SH601518,SH601555,SH601566,SH601567,SH601579,SH601588,SH601595,SH601599,SH601600,SH601601,SH601607,SH601608,SH601611,SH601616,SH601618,SH601619,SH601628,SH601633,SH601636,SH601666,SH601668,SH601669,SH601677,SH601678,SH601688,SH601689,SH601699,SH601700,SH601717,SH601718,SH601727,SH601766,SH601777,SH601788,SH601789,SH601798,SH601799,SH601800,SH601801,SH601808,SH601811,SH601818,SH601828,SH601838,SH601857,SH601858,SH601866,SH601872,SH601877,SH601878,SH601880,SH601881,SH601882,SH601886,SH601888,SH601890,SH601898,SH601899,SH601900,SH601901,SH601908,SH601918,SH601919,SH601928,SH601929,SH601933,SH601939,SH601949,SH601952,SH601958,SH601965,SH601966,SH601968,SH601969,SH601985,SH601988,SH601989,SH601991,SH601992,SH601996,SH601997,SH601998,SH601999,SH603000,SH603001,SH603002,SH603003,SH603005,SH603006,SH603007,SH603008,SH603009,SH603010,SH603011,SH603012,SH603015,SH603016,SH603017,SH603018,SH603019,SH603020,SH603021,SH603022,SH603023,SH603025,SH603026,SH603027,SH603028,SH603029,SH603030,SH603031,SH603032,SH603033,SH603035,SH603036,SH603037,SH603038,SH603039,SH603040,SH603041,SH603042,SH603043,SH603050,SH603055,SH603056,SH603058,SH603060,SH603063,SH603066,SH603067,SH603069,SH603076,SH603077,SH603078,SH603079,SH603080,SH603081,SH603083,SH603085,SH603086,SH603088,SH603089,SH603090,SH603096,SH603098,SH603099,SH603100,SH603101,SH603103,SH603106,SH603108,SH603110,SH603111,SH603113,SH603116,SH603117,SH603118,SH603123,SH603126,SH603127,SH603128,SH603129,SH603131,SH603133,SH603136,SH603138,SH603139,SH603157,SH603158,SH603159,SH603160,SH603161,SH603165,SH603166,SH603167,SH603168,SH603169,SH603177,SH603178,SH603179,SH603180,SH603181,SH603183,SH603186,SH603188,SH603189,SH603196,SH603197,SH603198,SH603199,SH603200,SH603203,SH603208,SH603218,SH603222,SH603223,SH603225,SH603226,SH603227,SH603228,SH603229,SH603232,SH603233,SH603238,SH603239,SH603258,SH603260,SH603266,SH603268,SH603269,SH603277,SH603278,SH603283,SH603286,SH603288,SH603289,SH603298,SH603299,SH603300,SH603302,SH603303,SH603305,SH603306,SH603308,SH603309,SH603311,SH603313,SH603315,SH603316,SH603318,SH603319,SH603320,SH603321,SH603322,SH603323,SH603326,SH603328,SH603329,SH603330,SH603331,SH603333,SH603335,SH603336,SH603337,SH603338,SH603339,SH603345,SH603355,SH603356,SH603357,SH603358,SH603359,SH603360,SH603363,SH603365,SH603366,SH603367,SH603368,SH603369,SH603377,SH603378,SH603380,SH603383,SH603385,SH603386,SH603387,SH603388,SH603389,SH603393,SH603396,SH603398,SH603399,SH603416,SH603421,SH603429,SH603444,SH603456,SH603458,SH603466,SH603477,SH603488,SH603496,SH603499,SH603500,SH603501,SH603505,SH603506,SH603507,SH603508,SH603515,SH603517,SH603518,SH603519,SH603520,SH603527,SH603528,SH603533,SH603535,SH603536,SH603538,SH603555,SH603556,SH603557,SH603558,SH603559,SH603566,SH603567,SH603568,SH603569,SH603577,SH603578,SH603579,SH603580,SH603585,SH603586,SH603587,SH603588,SH603589,SH603595,SH603598,SH603599,SH603600,SH603601,SH603602,SH603603,SH603605,SH603606,SH603607,SH603608,SH603609,SH603611,SH603612,SH603615,SH603616,SH603617,SH603618,SH603619,SH603626,SH603628,SH603630,SH603633,SH603636,SH603637,SH603638,SH603639,SH603648,SH603655,SH603656,SH603658,SH603659,SH603660,SH603661,SH603663,SH603665,SH603667,SH603668,SH603669,SH603676,SH603677,SH603678,SH603679,SH603680,SH603683,SH603685,SH603686,SH603688,SH603689,SH603690,SH603696,SH603698,SH603699,SH603701,SH603703,SH603707,SH603708,SH603711,SH603716,SH603717,SH603718,SH603721,SH603722,SH603725,SH603726,SH603727,SH603728,SH603729,SH603730,SH603737,SH603738,SH603757,SH603758,SH603766,SH603767,SH603768,SH603776,SH603777,SH603778,SH603779,SH603787,SH603788,SH603789,SH603797,SH603798,SH603799,SH603800,SH603801,SH603803,SH603806,SH603808,SH603809,SH603811,SH603813,SH603816,SH603817,SH603818,SH603819,SH603822,SH603823,SH603825,SH603826,SH603828,SH603829,SH603833,SH603838,SH603839,SH603843,SH603848,SH603855,SH603856,SH603858,SH603859,SH603860,SH603861,SH603866,SH603868,SH603869,SH603877,SH603878,SH603879,SH603880,SH603881,SH603882,SH603883,SH603885,SH603886,SH603887,SH603888,SH603889,SH603890,SH603896,SH603898,SH603899,SH603900,SH603901,SH603903,SH603906,SH603908,SH603909,SH603912,SH603916,SH603917,SH603918,SH603919,SH603920,SH603922,SH603926,SH603928,SH603929,SH603933,SH603936,SH603937,SH603938,SH603939,SH603955,SH603958,SH603959,SH603960,SH603963,SH603966,SH603968,SH603969,SH603970,SH603976,SH603977,SH603978,SH603979,SH603980,SH603985,SH603986,SH603987,SH603988,SH603989,SH603990,SH603991,SH603993,SH603996,SH603997,SH603998,SH603999,SH732056,SH732356,SH732506,SH780828,SH780838";

    public static final String STOCK_CODE_SZ = "SZ000001,SZ000002,SZ000004,SZ000005,SZ000006,SZ000007,SZ000008,SZ000009,SZ000010,SZ000011,SZ000012,SZ000014,SZ000016,SZ000017,SZ000018,SZ000019,SZ000020,SZ000021,SZ000022,SZ000023,SZ000025,SZ000026,SZ000027,SZ000028,SZ000029,SZ000030,SZ000031,SZ000032,SZ000034,SZ000035,SZ000036,SZ000037,SZ000038,SZ000039,SZ000040,SZ000042,SZ000043,SZ000045,SZ000046,SZ000048,SZ000049,SZ000050,SZ000055,SZ000056,SZ000058,SZ000059,SZ000060,SZ000061,SZ000062,SZ000063,SZ000065,SZ000066,SZ000068,SZ000069,SZ000070,SZ000078,SZ000088,SZ000089,SZ000090,SZ000096,SZ000099,SZ000100,SZ000150,SZ000151,SZ000153,SZ000155,SZ000156,SZ000157,SZ000158,SZ000159,SZ000166,SZ000301,SZ000333,SZ000338,SZ000400,SZ000401,SZ000402,SZ000403,SZ000404,SZ000407,SZ000408,SZ000409,SZ000410,SZ000411,SZ000413,SZ000415,SZ000416,SZ000417,SZ000418,SZ000419,SZ000420,SZ000421,SZ000422,SZ000423,SZ000425,SZ000426,SZ000428,SZ000429,SZ000430,SZ000488,SZ000498,SZ000501,SZ000502,SZ000503,SZ000504,SZ000505,SZ000506,SZ000507,SZ000509,SZ000510,SZ000511,SZ000513,SZ000514,SZ000516,SZ000517,SZ000518,SZ000519,SZ000520,SZ000521,SZ000523,SZ000524,SZ000525,SZ000526,SZ000528,SZ000529,SZ000530,SZ000531,SZ000532,SZ000533,SZ000534,SZ000536,SZ000537,SZ000538,SZ000539,SZ000540,SZ000541,SZ000543,SZ000544,SZ000545,SZ000546,SZ000547,SZ000548,SZ000550,SZ000551,SZ000552,SZ000553,SZ000554,SZ000555,SZ000557,SZ000558,SZ000559,SZ000560,SZ000561,SZ000563,SZ000564,SZ000565,SZ000566,SZ000567,SZ000568,SZ000570,SZ000571,SZ000572,SZ000573,SZ000576,SZ000581,SZ000582,SZ000584,SZ000585,SZ000586,SZ000587,SZ000589,SZ000590,SZ000591,SZ000592,SZ000593,SZ000595,SZ000596,SZ000597,SZ000598,SZ000599,SZ000600,SZ000601,SZ000603,SZ000605,SZ000606,SZ000607,SZ000608,SZ000609,SZ000610,SZ000611,SZ000612,SZ000613,SZ000615,SZ000616,SZ000617,SZ000619,SZ000620,SZ000622,SZ000623,SZ000625,SZ000626,SZ000627,SZ000628,SZ000629,SZ000630,SZ000631,SZ000632,SZ000633,SZ000635,SZ000636,SZ000637,SZ000638,SZ000639,SZ000650,SZ000651,SZ000652,SZ000655,SZ000656,SZ000657,SZ000659,SZ000661,SZ000662,SZ000663,SZ000665,SZ000666,SZ000667,SZ000668,SZ000669,SZ000670,SZ000671,SZ000672,SZ000673,SZ000676,SZ000677,SZ000678,SZ000679,SZ000680,SZ000681,SZ000682,SZ000683,SZ000685,SZ000686,SZ000687,SZ000688,SZ000690,SZ000691,SZ000692,SZ000693,SZ000695,SZ000697,SZ000698,SZ000700,SZ000701,SZ000702,SZ000703,SZ000705,SZ000707,SZ000708,SZ000709,SZ000710,SZ000711,SZ000712,SZ000713,SZ000715,SZ000716,SZ000717,SZ000718,SZ000719,SZ000720,SZ000721,SZ000722,SZ000723,SZ000725,SZ000726,SZ000727,SZ000728,SZ000729,SZ000731,SZ000732,SZ000733,SZ000735,SZ000736,SZ000737,SZ000738,SZ000739,SZ000750,SZ000751,SZ000752,SZ000753,SZ000755,SZ000756,SZ000757,SZ000758,SZ000759,SZ000760,SZ000761,SZ000762,SZ000766,SZ000767,SZ000768,SZ000776,SZ000777,SZ000778,SZ000779,SZ000780,SZ000782,SZ000783,SZ000785,SZ000786,SZ000788,SZ000789,SZ000790,SZ000791,SZ000792,SZ000793,SZ000795,SZ000796,SZ000797,SZ000798,SZ000799,SZ000800,SZ000801,SZ000802,SZ000803,SZ000806,SZ000807,SZ000809,SZ000810,SZ000811,SZ000812,SZ000813,SZ000815,SZ000816,SZ000818,SZ000819,SZ000820,SZ000821,SZ000822,SZ000823,SZ000825,SZ000826,SZ000828,SZ000829,SZ000830,SZ000831,SZ000833,SZ000835,SZ000836,SZ000837,SZ000838,SZ000839,SZ000848,SZ000850,SZ000851,SZ000852,SZ000856,SZ000858,SZ000859,SZ000860,SZ000861,SZ000862,SZ000863,SZ000868,SZ000869,SZ000875,SZ000876,SZ000877,SZ000878,SZ000880,SZ000881,SZ000882,SZ000883,SZ000885,SZ000886,SZ000887,SZ000888,SZ000889,SZ000890,SZ000892,SZ000893,SZ000895,SZ000897,SZ000898,SZ000899,SZ000900,SZ000901,SZ000902,SZ000903,SZ000905,SZ000906,SZ000908,SZ000909,SZ000910,SZ000911,SZ000912,SZ000913,SZ000915,SZ000917,SZ000918,SZ000919,SZ000920,SZ000921,SZ000922,SZ000923,SZ000925,SZ000926,SZ000927,SZ000928,SZ000929,SZ000930,SZ000931,SZ000932,SZ000933,SZ000935,SZ000936,SZ000937,SZ000938,SZ000939,SZ000948,SZ000949,SZ000950,SZ000951,SZ000952,SZ000953,SZ000955,SZ000957,SZ000958,SZ000959,SZ000960,SZ000961,SZ000962,SZ000963,SZ000965,SZ000966,SZ000967,SZ000968,SZ000969,SZ000970,SZ000971,SZ000972,SZ000973,SZ000975,SZ000976,SZ000977,SZ000978,SZ000979,SZ000980,SZ000981,SZ000982,SZ000983,SZ000985,SZ000987,SZ000988,SZ000989,SZ000990,SZ000993,SZ000995,SZ000996,SZ000997,SZ000998,SZ000999,SZ001696,SZ001896,SZ001965,SZ001979,SZ002001,SZ002002,SZ002003,SZ002004,SZ002005,SZ002006,SZ002007,SZ002008,SZ002009,SZ002010,SZ002011,SZ002012,SZ002013,SZ002014,SZ002015,SZ002016,SZ002017,SZ002018,SZ002019,SZ002020,SZ002021,SZ002022,SZ002023,SZ002024,SZ002025,SZ002026,SZ002027,SZ002028,SZ002029,SZ002030,SZ002031,SZ002032,SZ002033,SZ002034,SZ002035,SZ002036,SZ002037,SZ002038,SZ002039,SZ002040,SZ002041,SZ002042,SZ002043,SZ002044,SZ002045,SZ002046,SZ002047,SZ002048,SZ002049,SZ002050,SZ002051,SZ002052,SZ002053,SZ002054,SZ002055,SZ002056,SZ002057,SZ002058,SZ002059,SZ002060,SZ002061,SZ002062,SZ002063,SZ002064,SZ002065,SZ002066,SZ002067,SZ002068,SZ002069,SZ002070,SZ002071,SZ002072,SZ002073,SZ002074,SZ002075,SZ002076,SZ002077,SZ002078,SZ002079,SZ002080,SZ002081,SZ002082,SZ002083,SZ002084,SZ002085,SZ002086,SZ002087,SZ002088,SZ002089,SZ002090,SZ002091,SZ002092,SZ002093,SZ002094,SZ002095,SZ002096,SZ002097,SZ002098,SZ002099,SZ002100,SZ002101,SZ002102,SZ002103,SZ002104,SZ002105,SZ002106,SZ002107,SZ002108,SZ002109,SZ002110,SZ002111,SZ002112,SZ002113,SZ002114,SZ002115,SZ002116,SZ002117,SZ002118,SZ002119,SZ002120,SZ002121,SZ002122,SZ002123,SZ002124,SZ002125,SZ002126,SZ002127,SZ002128,SZ002129,SZ002130,SZ002131,SZ002132,SZ002133,SZ002134,SZ002135,SZ002136,SZ002137,SZ002138,SZ002139,SZ002140,SZ002141,SZ002142,SZ002143,SZ002144,SZ002145,SZ002146,SZ002147,SZ002148,SZ002149,SZ002150,SZ002151,SZ002152,SZ002153,SZ002154,SZ002155,SZ002156,SZ002157,SZ002158,SZ002159,SZ002160,SZ002161,SZ002162,SZ002163,SZ002164,SZ002165,SZ002166,SZ002167,SZ002168,SZ002169,SZ002170,SZ002171,SZ002172,SZ002173,SZ002174,SZ002175,SZ002176,SZ002177,SZ002178,SZ002179,SZ002180,SZ002181,SZ002182,SZ002183,SZ002184,SZ002185,SZ002186,SZ002187,SZ002188,SZ002189,SZ002190,SZ002191,SZ002192,SZ002193,SZ002194,SZ002195,SZ002196,SZ002197,SZ002198,SZ002199,SZ002200,SZ002201,SZ002202,SZ002203,SZ002204,SZ002205,SZ002206,SZ002207,SZ002208,SZ002209,SZ002210,SZ002211,SZ002212,SZ002213,SZ002214,SZ002215,SZ002216,SZ002217,SZ002218,SZ002219,SZ002220,SZ002221,SZ002222,SZ002223,SZ002224,SZ002225,SZ002226,SZ002227,SZ002228,SZ002229,SZ002230,SZ002231,SZ002232,SZ002233,SZ002234,SZ002235,SZ002236,SZ002237,SZ002238,SZ002239,SZ002240,SZ002241,SZ002242,SZ002243,SZ002244,SZ002245,SZ002246,SZ002247,SZ002248,SZ002249,SZ002250,SZ002251,SZ002252,SZ002253,SZ002254,SZ002255,SZ002256,SZ002258,SZ002259,SZ002260,SZ002261,SZ002262,SZ002263,SZ002264,SZ002265,SZ002266,SZ002267,SZ002268,SZ002269,SZ002270,SZ002271,SZ002272,SZ002273,SZ002274,SZ002275,SZ002276,SZ002277,SZ002278,SZ002279,SZ002280,SZ002281,SZ002282,SZ002283,SZ002284,SZ002285,SZ002286,SZ002287,SZ002288,SZ002289,SZ002290,SZ002291,SZ002292,SZ002293,SZ002294,SZ002295,SZ002296,SZ002297,SZ002298,SZ002299,SZ002300,SZ002301,SZ002302,SZ002303,SZ002304,SZ002305,SZ002306,SZ002307,SZ002308,SZ002309,SZ002310,SZ002311,SZ002312,SZ002313,SZ002314,SZ002315,SZ002316,SZ002317,SZ002318,SZ002319,SZ002320,SZ002321,SZ002322,SZ002323,SZ002324,SZ002325,SZ002326,SZ002327,SZ002328,SZ002329,SZ002330,SZ002331,SZ002332,SZ002333,SZ002334,SZ002335,SZ002336,SZ002337,SZ002338,SZ002339,SZ002340,SZ002341,SZ002342,SZ002343,SZ002344,SZ002345,SZ002346,SZ002347,SZ002348,SZ002349,SZ002350,SZ002351,SZ002352,SZ002353,SZ002354,SZ002355,SZ002356,SZ002357,SZ002358,SZ002359,SZ002360,SZ002361,SZ002362,SZ002363,SZ002364,SZ002365,SZ002366,SZ002367,SZ002368,SZ002369,SZ002370,SZ002371,SZ002372,SZ002373,SZ002374,SZ002375,SZ002376,SZ002377,SZ002378,SZ002379,SZ002380,SZ002381,SZ002382,SZ002383,SZ002384,SZ002385,SZ002386,SZ002387,SZ002388,SZ002389,SZ002390,SZ002391,SZ002392,SZ002393,SZ002394,SZ002395,SZ002396,SZ002397,SZ002398,SZ002399,SZ002400,SZ002401,SZ002402,SZ002403,SZ002404,SZ002405,SZ002406,SZ002407,SZ002408,SZ002409,SZ002410,SZ002411,SZ002412,SZ002413,SZ002414,SZ002415,SZ002416,SZ002417,SZ002418,SZ002419,SZ002420,SZ002421,SZ002422,SZ002423,SZ002424,SZ002425,SZ002426,SZ002427,SZ002428,SZ002429,SZ002430,SZ002431,SZ002432,SZ002433,SZ002434,SZ002435,SZ002436,SZ002437,SZ002438,SZ002439,SZ002440,SZ002441,SZ002442,SZ002443,SZ002444,SZ002445,SZ002446,SZ002447,SZ002448,SZ002449,SZ002450,SZ002451,SZ002452,SZ002453,SZ002454,SZ002455,SZ002456,SZ002457,SZ002458,SZ002459,SZ002460,SZ002461,SZ002462,SZ002463,SZ002464,SZ002465,SZ002466,SZ002467,SZ002468,SZ002469,SZ002470,SZ002471,SZ002472,SZ002473,SZ002474,SZ002475,SZ002476,SZ002477,SZ002478,SZ002479,SZ002480,SZ002481,SZ002482,SZ002483,SZ002484,SZ002485,SZ002486,SZ002487,SZ002488,SZ002489,SZ002490,SZ002491,SZ002492,SZ002493,SZ002494,SZ002495,SZ002496,SZ002497,SZ002498,SZ002499,SZ002500,SZ002501,SZ002502,SZ002503,SZ002504,SZ002505,SZ002506,SZ002507,SZ002508,SZ002509,SZ002510,SZ002511,SZ002512,SZ002513,SZ002514,SZ002515,SZ002516,SZ002517,SZ002518,SZ002519,SZ002520,SZ002521,SZ002522,SZ002523,SZ002524,SZ002526,SZ002527,SZ002528,SZ002529,SZ002530,SZ002531,SZ002532,SZ002533,SZ002534,SZ002535,SZ002536,SZ002537,SZ002538,SZ002539,SZ002540,SZ002541,SZ002542,SZ002543,SZ002544,SZ002545,SZ002546,SZ002547,SZ002548,SZ002549,SZ002550,SZ002551,SZ002552,SZ002553,SZ002554,SZ002555,SZ002556,SZ002557,SZ002558,SZ002559,SZ002560,SZ002561,SZ002562,SZ002563,SZ002564,SZ002565,SZ002566,SZ002567,SZ002568,SZ002569,SZ002570,SZ002571,SZ002572,SZ002573,SZ002574,SZ002575,SZ002576,SZ002577,SZ002578,SZ002579,SZ002580,SZ002581,SZ002582,SZ002583,SZ002584,SZ002585,SZ002586,SZ002587,SZ002588,SZ002589,SZ002590,SZ002591,SZ002592,SZ002593,SZ002594,SZ002595,SZ002596,SZ002597,SZ002598,SZ002599,SZ002600,SZ002601,SZ002602,SZ002603,SZ002604,SZ002605,SZ002606,SZ002607,SZ002608,SZ002609,SZ002610,SZ002611,SZ002612,SZ002613,SZ002614,SZ002615,SZ002616,SZ002617,SZ002618,SZ002619,SZ002620,SZ002621,SZ002622,SZ002623,SZ002624,SZ002625,SZ002626,SZ002627,SZ002628,SZ002629,SZ002630,SZ002631,SZ002632,SZ002633,SZ002634,SZ002635,SZ002636,SZ002637,SZ002638,SZ002639,SZ002640,SZ002641,SZ002642,SZ002643,SZ002644,SZ002645,SZ002646,SZ002647,SZ002648,SZ002649,SZ002650,SZ002651,SZ002652,SZ002653,SZ002654,SZ002655,SZ002656,SZ002657,SZ002658,SZ002659,SZ002660,SZ002661,SZ002662,SZ002663,SZ002664,SZ002665,SZ002666,SZ002667,SZ002668,SZ002669,SZ002670,SZ002671,SZ002672,SZ002673,SZ002674,SZ002675,SZ002676,SZ002677,SZ002678,SZ002679,SZ002680,SZ002681,SZ002682,SZ002683,SZ002684,SZ002685,SZ002686,SZ002687,SZ002688,SZ002689,SZ002690,SZ002691,SZ002692,SZ002693,SZ002694,SZ002695,SZ002696,SZ002697,SZ002698,SZ002699,SZ002700,SZ002701,SZ002702,SZ002703,SZ002705,SZ002706,SZ002707,SZ002708,SZ002709,SZ002711,SZ002712,SZ002713,SZ002714,SZ002715,SZ002716,SZ002717,SZ002718,SZ002719,SZ002721,SZ002722,SZ002723,SZ002724,SZ002725,SZ002726,SZ002727,SZ002728,SZ002729,SZ002730,SZ002731,SZ002732,SZ002733,SZ002734,SZ002735,SZ002736,SZ002737,SZ002738,SZ002739,SZ002740,SZ002741,SZ002742,SZ002743,SZ002745,SZ002746,SZ002747,SZ002748,SZ002749,SZ002750,SZ002751,SZ002752,SZ002753,SZ002755,SZ002756,SZ002757,SZ002758,SZ002759,SZ002760,SZ002761,SZ002762,SZ002763,SZ002765,SZ002766,SZ002767,SZ002768,SZ002769,SZ002770,SZ002771,SZ002772,SZ002773,SZ002774,SZ002775,SZ002776,SZ002777,SZ002778,SZ002779,SZ002780,SZ002781,SZ002782,SZ002783,SZ002785,SZ002786,SZ002787,SZ002788,SZ002789,SZ002790,SZ002791,SZ002792,SZ002793,SZ002795,SZ002796,SZ002797,SZ002798,SZ002799,SZ002800,SZ002801,SZ002802,SZ002803,SZ002805,SZ002806,SZ002807,SZ002808,SZ002809,SZ002810,SZ002811,SZ002812,SZ002813,SZ002815,SZ002816,SZ002817,SZ002818,SZ002819,SZ002820,SZ002821,SZ002822,SZ002823,SZ002824,SZ002825,SZ002826,SZ002827,SZ002828,SZ002829,SZ002830,SZ002831,SZ002832,SZ002833,SZ002835,SZ002836,SZ002837,SZ002838,SZ002839,SZ002840,SZ002841,SZ002842,SZ002843,SZ002845,SZ002846,SZ002847,SZ002848,SZ002849,SZ002850,SZ002851,SZ002852,SZ002853,SZ002855,SZ002856,SZ002857,SZ002858,SZ002859,SZ002860,SZ002861,SZ002862,SZ002863,SZ002864,SZ002865,SZ002866,SZ002867,SZ002868,SZ002869,SZ002870,SZ002871,SZ002872,SZ002873,SZ002875,SZ002876,SZ002877,SZ002878,SZ002879,SZ002880,SZ002881,SZ002882,SZ002883,SZ002884,SZ002885,SZ002886,SZ002887,SZ002888,SZ002889,SZ002890,SZ002891,SZ002892,SZ002893,SZ002895,SZ002896,SZ002897,SZ002898,SZ002899,SZ002900,SZ002901,SZ002902,SZ002903,SZ002905,SZ002906,SZ002907,SZ002908,SZ002909,SZ002910,SZ002911,SZ002912,SZ002913,SZ002915,SZ002916,SZ002917,SZ002918,SZ002919,SZ002920,SZ002921,SZ002922,SZ002923,SZ002925,SZ300001,SZ300002,SZ300003,SZ300004,SZ300005,SZ300006,SZ300007,SZ300008,SZ300009,SZ300010,SZ300011,SZ300012,SZ300013,SZ300014,SZ300015,SZ300016,SZ300017,SZ300018,SZ300019,SZ300020,SZ300021,SZ300022,SZ300023,SZ300024,SZ300025,SZ300026,SZ300027,SZ300028,SZ300029,SZ300030,SZ300031,SZ300032,SZ300033,SZ300034,SZ300035,SZ300036,SZ300037,SZ300038,SZ300039,SZ300040,SZ300041,SZ300042,SZ300043,SZ300044,SZ300045,SZ300046,SZ300047,SZ300048,SZ300049,SZ300050,SZ300051,SZ300052,SZ300053,SZ300054,SZ300055,SZ300056,SZ300057,SZ300058,SZ300059,SZ300061,SZ300062,SZ300063,SZ300064,SZ300065,SZ300066,SZ300067,SZ300068,SZ300069,SZ300070,SZ300071,SZ300072,SZ300073,SZ300074,SZ300075,SZ300076,SZ300077,SZ300078,SZ300079,SZ300080,SZ300081,SZ300082,SZ300083,SZ300084,SZ300085,SZ300086,SZ300087,SZ300088,SZ300089,SZ300090,SZ300091,SZ300092,SZ300093,SZ300094,SZ300095,SZ300096,SZ300097,SZ300098,SZ300099,SZ300100,SZ300101,SZ300102,SZ300103,SZ300104,SZ300105,SZ300106,SZ300107,SZ300108,SZ300109,SZ300110,SZ300111,SZ300112,SZ300113,SZ300114,SZ300115,SZ300116,SZ300117,SZ300118,SZ300119,SZ300120,SZ300121,SZ300122,SZ300123,SZ300124,SZ300125,SZ300126,SZ300127,SZ300128,SZ300129,SZ300130,SZ300131,SZ300132,SZ300133,SZ300134,SZ300135,SZ300136,SZ300137,SZ300138,SZ300139,SZ300140,SZ300141,SZ300142,SZ300143,SZ300144,SZ300145,SZ300146,SZ300147,SZ300148,SZ300149,SZ300150,SZ300151,SZ300152,SZ300153,SZ300154,SZ300155,SZ300156,SZ300157,SZ300158,SZ300159,SZ300160,SZ300161,SZ300162,SZ300163,SZ300164,SZ300165,SZ300166,SZ300167,SZ300168,SZ300169,SZ300170,SZ300171,SZ300172,SZ300173,SZ300174,SZ300175,SZ300176,SZ300177,SZ300178,SZ300179,SZ300180,SZ300181,SZ300182,SZ300183,SZ300184,SZ300185,SZ300187,SZ300188,SZ300189,SZ300190,SZ300191,SZ300192,SZ300193,SZ300194,SZ300195,SZ300196,SZ300197,SZ300198,SZ300199,SZ300200,SZ300201,SZ300202,SZ300203,SZ300204,SZ300205,SZ300206,SZ300207,SZ300208,SZ300209,SZ300210,SZ300211,SZ300212,SZ300213,SZ300214,SZ300215,SZ300216,SZ300217,SZ300218,SZ300219,SZ300220,SZ300221,SZ300222,SZ300223,SZ300224,SZ300225,SZ300226,SZ300227,SZ300228,SZ300229,SZ300230,SZ300231,SZ300232,SZ300233,SZ300234,SZ300235,SZ300236,SZ300237,SZ300238,SZ300239,SZ300240,SZ300241,SZ300242,SZ300243,SZ300244,SZ300245,SZ300246,SZ300247,SZ300248,SZ300249,SZ300250,SZ300251,SZ300252,SZ300253,SZ300254,SZ300255,SZ300256,SZ300257,SZ300258,SZ300259,SZ300260,SZ300261,SZ300262,SZ300263,SZ300264,SZ300265,SZ300266,SZ300267,SZ300268,SZ300269,SZ300270,SZ300271,SZ300272,SZ300273,SZ300274,SZ300275,SZ300276,SZ300277,SZ300278,SZ300279,SZ300280,SZ300281,SZ300282,SZ300283,SZ300284,SZ300285,SZ300286,SZ300287,SZ300288,SZ300289,SZ300290,SZ300291,SZ300292,SZ300293,SZ300294,SZ300295,SZ300296,SZ300297,SZ300298,SZ300299,SZ300300,SZ300301,SZ300302,SZ300303,SZ300304,SZ300305,SZ300306,SZ300307,SZ300308,SZ300309,SZ300310,SZ300311,SZ300312,SZ300313,SZ300314,SZ300315,SZ300316,SZ300317,SZ300318,SZ300319,SZ300320,SZ300321,SZ300322,SZ300323,SZ300324,SZ300325,SZ300326,SZ300327,SZ300328,SZ300329,SZ300330,SZ300331,SZ300332,SZ300333,SZ300334,SZ300335,SZ300336,SZ300337,SZ300338,SZ300339,SZ300340,SZ300341,SZ300342,SZ300343,SZ300344,SZ300345,SZ300346,SZ300347,SZ300348,SZ300349,SZ300350,SZ300351,SZ300352,SZ300353,SZ300354,SZ300355,SZ300356,SZ300357,SZ300358,SZ300359,SZ300360,SZ300362,SZ300363,SZ300364,SZ300365,SZ300366,SZ300367,SZ300368,SZ300369,SZ300370,SZ300371,SZ300373,SZ300374,SZ300375,SZ300376,SZ300377,SZ300378,SZ300379,SZ300380,SZ300381,SZ300382,SZ300383,SZ300384,SZ300385,SZ300386,SZ300387,SZ300388,SZ300389,SZ300390,SZ300391,SZ300392,SZ300393,SZ300394,SZ300395,SZ300396,SZ300397,SZ300398,SZ300399,SZ300400,SZ300401,SZ300402,SZ300403,SZ300404,SZ300405,SZ300406,SZ300407,SZ300408,SZ300409,SZ300410,SZ300411,SZ300412,SZ300413,SZ300414,SZ300415,SZ300416,SZ300417,SZ300418,SZ300419,SZ300420,SZ300421,SZ300422,SZ300423,SZ300424,SZ300425,SZ300426,SZ300427,SZ300428,SZ300429,SZ300430,SZ300431,SZ300432,SZ300433,SZ300434,SZ300435,SZ300436,SZ300437,SZ300438,SZ300439,SZ300440,SZ300441,SZ300442,SZ300443,SZ300444,SZ300445,SZ300446,SZ300447,SZ300448,SZ300449,SZ300450,SZ300451,SZ300452,SZ300453,SZ300455,SZ300456,SZ300457,SZ300458,SZ300459,SZ300460,SZ300461,SZ300462,SZ300463,SZ300464,SZ300465,SZ300466,SZ300467,SZ300468,SZ300469,SZ300470,SZ300471,SZ300472,SZ300473,SZ300474,SZ300475,SZ300476,SZ300477,SZ300478,SZ300479,SZ300480,SZ300481,SZ300482,SZ300483,SZ300484,SZ300485,SZ300486,SZ300487,SZ300488,SZ300489,SZ300490,SZ300491,SZ300492,SZ300493,SZ300494,SZ300495,SZ300496,SZ300497,SZ300498,SZ300499,SZ300500,SZ300501,SZ300502,SZ300503,SZ300505,SZ300506,SZ300507,SZ300508,SZ300509,SZ300510,SZ300511,SZ300512,SZ300513,SZ300514,SZ300515,SZ300516,SZ300517,SZ300518,SZ300519,SZ300520,SZ300521,SZ300522,SZ300523,SZ300525,SZ300526,SZ300527,SZ300528,SZ300529,SZ300530,SZ300531,SZ300532,SZ300533,SZ300534,SZ300535,SZ300536,SZ300537,SZ300538,SZ300539,SZ300540,SZ300541,SZ300542,SZ300543,SZ300545,SZ300546,SZ300547,SZ300548,SZ300549,SZ300550,SZ300551,SZ300552,SZ300553,SZ300554,SZ300555,SZ300556,SZ300557,SZ300558,SZ300559,SZ300560,SZ300561,SZ300562,SZ300563,SZ300565,SZ300566,SZ300567,SZ300568,SZ300569,SZ300570,SZ300571,SZ300572,SZ300573,SZ300575,SZ300576,SZ300577,SZ300578,SZ300579,SZ300580,SZ300581,SZ300582,SZ300583,SZ300584,SZ300585,SZ300586,SZ300587,SZ300588,SZ300589,SZ300590,SZ300591,SZ300592,SZ300593,SZ300595,SZ300596,SZ300597,SZ300598,SZ300599,SZ300600,SZ300601,SZ300602,SZ300603,SZ300604,SZ300605,SZ300606,SZ300607,SZ300608,SZ300609,SZ300610,SZ300611,SZ300612,SZ300613,SZ300615,SZ300616,SZ300617,SZ300618,SZ300619,SZ300620,SZ300621,SZ300622,SZ300623,SZ300624,SZ300625,SZ300626,SZ300627,SZ300628,SZ300629,SZ300630,SZ300631,SZ300632,SZ300633,SZ300635,SZ300636,SZ300637,SZ300638,SZ300639,SZ300640,SZ300641,SZ300642,SZ300643,SZ300645,SZ300646,SZ300647,SZ300648,SZ300649,SZ300650,SZ300651,SZ300652,SZ300653,SZ300654,SZ300655,SZ300656,SZ300657,SZ300658,SZ300659,SZ300660,SZ300661,SZ300662,SZ300663,SZ300664,SZ300665,SZ300666,SZ300667,SZ300668,SZ300669,SZ300670,SZ300671,SZ300672,SZ300673,SZ300675,SZ300676,SZ300677,SZ300678,SZ300679,SZ300680,SZ300681,SZ300682,SZ300683,SZ300684,SZ300685,SZ300686,SZ300687,SZ300688,SZ300689,SZ300690,SZ300691,SZ300692,SZ300693,SZ300695,SZ300696,SZ300697,SZ300698,SZ300699,SZ300700,SZ300701,SZ300702,SZ300703,SZ300705,SZ300706,SZ300707,SZ300708,SZ300709,SZ300710,SZ300711,SZ300712,SZ300713,SZ300715,SZ300716,SZ300717,SZ300718,SZ300719,SZ300720,SZ300721,SZ300722,SZ300723,SZ300725,SZ300726,SZ300727,SZ300728,SZ300729,SZ300730,SZ300731,SZ300732,SZ300733,SZ300735,SZ300736";

}



