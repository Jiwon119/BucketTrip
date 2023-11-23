// import "./assets/main.css";

import { createApp } from "vue";
import { createPinia } from "pinia";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";

import App from "./App.vue";
import router from "./router";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

// /* import the fontawesome core */
import { library } from "@fortawesome/fontawesome-svg-core";

// /* import font awesome icon component */
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

// /* import specific icons */
import { faUserSecret, faCampground } from "@fortawesome/free-solid-svg-icons";
// import { campground } from "@fortawesome/free-solid-svg-icons";

// /* add icons to the library */
library.add(faUserSecret);
library.add(faCampground);

const app = createApp(App);
const pinia = createPinia();

pinia.use(piniaPluginPersistedstate);

app.component("font-awesome-icon", FontAwesomeIcon);

app.use(pinia);
// app.use(createPinia());
app.use(router);

app.mount("#app");
