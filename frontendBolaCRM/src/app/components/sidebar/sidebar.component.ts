import { Component, OnInit } from '@angular/core';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Inicio',  icon: 'dashboard', class: '' },
    { path: '/user-profile', title: 'Meus Lembretes',  icon:'person', class: '' },
    { path: '/table-list', title: 'Meus Cultos',  icon:'content_paste', class: '' },
    { path: '/typography', title: 'Escalas',  icon:'library_books', class: '' },
    { path: '/icons', title: 'Gerenciar Mural',  icon:'bubble_chart', class: '' },
    { path: '/maps', title: 'Pastores',  icon:'location_on', class: '' },
    { path: '/notifications', title: 'Usuários',  icon:'notifications', class: '' },
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
  isMobileMenu() {
      if ($(window).width() > 991) {
          return false;
      }
      return true;
  };
}
