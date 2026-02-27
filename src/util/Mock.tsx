import {text} from "@fortawesome/fontawesome-svg-core";

export default class Mock {

  static fetch(response: any) {
    return jest.fn().mockImplementation(() =>
      Promise.resolve({
        ok: true,
        json: () => response,
      })
    )
  }

}