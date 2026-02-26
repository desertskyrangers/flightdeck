import Icons from './util/Icons'
import './css/nav.css'
import {Link} from "react-router";
import AppPath from "./AppPath";
import React from 'react';

export default function Menu() {

  return (
    <div className='nav-bar'>
      <NavButton to={AppPath.HOME} icon={Icons.FLIGHTDECK}/>
      <NavButton to={AppPath.USER_FLIGHTS} icon={Icons.FLIGHTS}/>
      <NavButton to={AppPath.SETUP} icon={Icons.SETUP}/>
      <NavButton to={AppPath.USER} icon={Icons.USER}/>
    </div>
  )

}

function NavButton(props: any) {

  return (
    <Link to={props.to}>
      <div className='nav-button'>{props.icon}</div>
    </Link>
  )

}
