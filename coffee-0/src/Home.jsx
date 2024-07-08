import React from "react";
import {Hero} from "./components/General/Hero/Hero.jsx";
import {Navbar} from "./components/General/Navbar/Navbar.jsx";
import {Services} from "./components/General/Services/Services.jsx";
import {Bottom} from "./components/General/Bottom/bottom.jsx";
import "aos/dist/aos.css";

const Home = () => {
    return (
      <div className="bg-white dark:bg-gray-900 dark:text-white duration-200 overflow-x-hidden">
        <Navbar />
        <Hero />
        <Services />
        <Bottom />
      </div>
    );
  };

export default Home;

