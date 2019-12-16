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
    { path: '/notifications', title: 'Meus Lembretes',  icon:'notifications', class: '' },
    { path: '/table-list', title: 'Meus Cultos',  icon:'content_paste', class: '' },
    { path: '/typography', title: 'Escalas',  icon:'library_books', class: '' },
    { path: '/icons', title: 'Gerenciar Mural',  icon:'bubble_chart', class: '' },
    { path: '/maps', title: 'Pastores',  icon:'location_on', class: '' },
    { path: '/usuario', title: 'Perfil Usuário',  icon:'person', class: '' },
    { path: '/login', title: 'Login',  icon:'person', class: '' },
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
