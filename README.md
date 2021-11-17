# Özgür Yazılım
## Film Koleksiyon Uygulaması


![Alt text](https://wallpaperaccess.com/full/329633.jpg "Title")



Uygulama Özellikleri:

* Film Tanımı ( Ad, Yayın Yılı, Tür, Açıklama, Medya, Dil seçenekleri ) ✓
* Bir film için n tane Oyuncu tanımı yapılabilmeli ( Ad Soyad, isteğe
bağlı rol ) ✓
* Film Adı, Oyuncu Adı, Tür ile arama yapılabilmeli, yıla göre sıralama
yapılabilmeli ✓
* Var olan bir kayıt üzerinde değişiklik yapılabilmeli ✓
* Var olan kayıtlar incelebilmeli ✓
* Var olan bir kayıt silinebilmeli ✓

Bir adım öne çıkayım derseniz:
* Kod standartlarına uygun yazılım geliştirmeniz artı olarak
değerlendirilir. ✓
* Birim test içermesi elbette bir artı olarak değerlendirilir. ✓
* Kullanıcılar sisteme kullanıcı adı/parola ile giriş yapmalı
* Bir yetkilendirme mekanizması içermeli. Örneğin standart kullanıcılar
kayıt oluşturabilsin ilişkilendirebilsin fakat silemesin. Yönetici tüm
eylemleri gerçekleştirebilsin.



## Kullanılan Teknolojiler

- Spring Boot
- Thymeleaf
- JPA
- Maven
- Bootstrap
- Lombok
- PostgreSQL

Proje kurulumu için aşağıdaki gerekli adımları sırayla uygulamanız gerekmektedir.

## Editör Kurulumu



- [Eclipse] - editörünü kurmanız gerekmektedir.
- [Java JDK 11] - en az kurulu olması gerekmektedir.
- [Lombok] - kurulması gerekmektedir.
- [PostgreSQL] - kurulması gerekmektedir.




## Proje Kurulumu

Proje kurulumu için gerekli adımları takip ediniz.

Projeyi bilgisayarınıza indiriniz.
Ardından PostgreSQL indiriniz.
PostgreSQL indirdikten sonra ve projeyi Eclipse'de açtıktan sonra application.properties kısmında bu kodları yapıştırınız.
username ve password kısmını siz PostgreSQL yüklerken belirliyorsunuz ona göre değişim yapabilirsiniz. Ardından kaydediyorsunuz.

```sh
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/MovieCollection
spring.datasource.username=postgres
spring.datasource.password=12345
spring.jpa.properties.javax.persistence.validation.mode = none
```

Daha sonra PostgreSQL'de pgAdmin'de bir veritabanı oluşturun ve adına MovieCollection diyin.
Entities katmanındaki concretes içindeki sınıfların içinde tek tek kaydet işlemi yapın bu şekilde ilişkili tablolar PostgreSQL tarafına yansıyacaktır zira code-first yaklaşımı kullanılmaktadır. Ardından projeyi localhost'ta açıp ilgili işlemleri yapabilirsiniz.




   
   
