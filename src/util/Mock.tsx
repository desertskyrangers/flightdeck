export default class Mock {

  static fetch(response: {}) {
    return jest.fn().mockImplementation(() =>
      Promise.resolve({
        ok: true,
        json: () => response,
      })
    );
  }

}