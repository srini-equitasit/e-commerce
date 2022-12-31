import {Component, OnInit} from '@angular/core';
import {AuthService} from '@auth0/auth0-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ECommerce Application';

  loading: boolean = false;

  constructor(public auth: AuthService) {
  }

  ngOnInit() {

    this.auth.isLoading$.subscribe(data => {
      this.loading = data;
    });


  }


}
