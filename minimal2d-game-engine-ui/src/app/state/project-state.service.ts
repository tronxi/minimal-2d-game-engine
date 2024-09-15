import {Injectable} from '@angular/core';
import {Subject} from "rxjs";
import {SelectedEvent} from "./SelectedEvent";

@Injectable({
  providedIn: 'root'
})
export class ProjectStateService {

  private _selectedEventState = new Subject<SelectedEvent>()

  get state$() {
    return this._selectedEventState.asObservable();
  }

  update(selectedEvent: SelectedEvent) {
    this._selectedEventState.next(selectedEvent);
  }
}
