# E-Commerce

---

## Configuração mínima
+ minSdkVersion: 19
+ targetSdkVersion: 29
+ gradle_version: 3.6.2


```

├──infrastructure ----------------| Módulos referentes a camada de infra do app.
│       ├── event ----------------| Módulos referentes a comunicação web.
│       │     ├── response -------| Camada onde se concentra utils do retrofit e a criação dos clients.
│       │     ├── retrofit -------| Modulo de concatenação de datasets que abstraem conectividade com a web.
│       │     ├── service --------| End-points do retrofit.
│       └── Util -----------------| Útil referentes a camada de infra do app.
├── ui -------------------------- | Camada de apresentação do app, aqui onde fica todas as views do app.
│   ├── activity ---------------- | Activities referentes do projeto.
│   ├── adapter ----------------- | Todos os adaptes referente ao projeto.
│   ├── fragment ---------------- | Fagments das classes Home, Product e ProductDetals.
│   └── viewmodel --------------- | Views models das classes Home, Product e ProductDetals
└──util ------------------------- | Util do App.

## Bibliotecas e Frameworks definidos

- LiveData
- ViewModel
- Data Bind
- Navigation
- Retrofit
- Gson
- Glide
- RecyclerView
- carouselview