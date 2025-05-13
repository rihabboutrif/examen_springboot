import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { RoleService } from '../services/role.service';
import { PermissionService } from '../services/permission.service';
import { HistoriqueService } from '../services/historique.service';
import { NgApexchartsModule } from 'ng-apexcharts'; // ✅ important
import { CommonModule } from '@angular/common';
import {
  ApexChart,
  ApexAxisChartSeries,
  ApexNonAxisChartSeries,
  ApexXAxis
} from 'ng-apexcharts';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  imports: [CommonModule, NgApexchartsModule], // ✅ importe les modules nécessaires ici

})
export class DashboardComponent implements OnInit {
  userCount = 0;
  roleCount = 0;
  permissionCount = 0;
  todayActions = 0;

  historiques: any[] = [];

 roleChart: {
  series: ApexNonAxisChartSeries;
  chart: ApexChart;
  labels: string[];
  colors: string[];
} = {
  series: [],
  chart: {
    type: 'donut',
    height: 250
  },
  labels: [],
  colors: ['#4CAF50', '#2196F3', '#FFC107', '#E91E63']
};

historyChart: {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
} = {
  series: [],
  chart: {
    type: 'line',
    height: 250
  },
  xaxis: {
    categories: []
  }
};



  constructor(
    private userService: UserService,
    private roleService: RoleService,
    private permissionService: PermissionService,
    private historiqueActionService: HistoriqueService
  ) {}

  ngOnInit(): void {
    this.loadStats();
    this.loadCharts();
  }

  loadStats() {
    this.userService.count().subscribe(count => {
      this.userCount = count;
    });

    this.roleService.count().subscribe(count => {
      this.roleCount = count;
    });

    this.permissionService.count().subscribe(count => {
      this.permissionCount = count;
    });

    this.historiqueActionService.getAll().subscribe(hist => {
      const today = new Date().toDateString();
      this.historiques = hist;
      this.todayActions = hist.filter((h: any) => new Date(h.date).toDateString() === today).length;
    });
  }

  loadCharts() {
    this.userService.getAllPaginated(0, 1000).subscribe(data => {
      const users = data.content || [];
      const roleMap: { [role: string]: number } = {};
      users.forEach((u: any) => {
        const roleName = u.role?.nom || 'Inconnu';
        roleMap[roleName] = (roleMap[roleName] || 0) + 1;
      });

      this.roleChart.series = Object.values(roleMap);
      this.roleChart.labels = Object.keys(roleMap);
    });

    this.historiqueActionService.getLast7Days().subscribe(data => {
      this.historyChart.series = [
        {
          name: 'Actions',
          data: data.map((d: any) => d.count)
        }
      ];
      this.historyChart.xaxis.categories = data.map((d: any) => d.day);
    });
  }
}
