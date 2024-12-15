// src/font-awesome.js
import { library } from '@fortawesome/fontawesome-svg-core';
import { faTachometerAlt, faShoppingCart, faUser, faBox, faCog, faChartLine, faPhone, faBell, faQuestionCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

// Add icons to the library
library.add(faTachometerAlt, faShoppingCart, faUser, faBox, faCog, faChartLine, faPhone, faBell, faQuestionCircle);

// Export the FontAwesomeIcon component for global use
export default FontAwesomeIcon;
