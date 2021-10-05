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



## Proje Hakkında

Projeyi yazarken kurumsal mimari yaklaşımlarından biri olan katmanlı mimari ile yapıyı kurdum. Bunu yapma sebebim kod tekrarını önlemek, projeye esneklik sağlamak ve proje yönetimini kolaylaştırmakdı. Bununla yetinmeyip SOLID prensiplerinin 5.harfi olan "Dependency Inversion Principle" olan ve "Dependency Injection" patterni ile kullanılan enjecte yöntemini gerek controller'da gerek business tarafında kullandım peki bu bana ne sağladı diye sorarsanız bu bana gevşek bağımlılık sağladı buda mümkün olan bağımlılıkları en aza indirgememi sağlamış oldu.
Mimari hakkında konuştuktan sonra biraz da projemize bakacak olursak bizden istenilenleri okuyalım öncelikle ilk madde ne diyor Film tanımı ve gerekli özellikler verilmiş bakıldığında Dil Seçenekleri dışında diğerleri Film adında bir sınıf oluşturup içinde  tanımlanabilecek özellikler iken Dil Seçenekleri ise başlı başına farklı bir sınıf olmalı zira bir filmin birden fazla dil seçeneği olabilir bunu belirtmek gerekir. Diğer madde ise 1 film için n tane oyuncu tanımlanabilmeli yani 1 tane de olabilir 1000 tane de buradaki mantık ise film sınıfından oyuncu adında bir sınıf oluşturulup bire çok ilişki verilmesi gerekir ve önce film ekleme işlemi yapılır daha sonra oyuncu eklenirken burada hangi filme eklemek istediği seçtirilir. Diğer madde ise arama yapmamızı istemiş bunun içinde hangi özellikte aramamız belirtilmiş bunlarla ilgili query yazmamız gerekir ve ilgili query yazıldıktan sonra bu fonksiyonları kullanmak gerekir ayrıca bizden yıllara göre sıralama yapmamızı da istemiş buda aslında çok basit bir kaç kod satırıyla yapılacak bir durum bunu da JPA ile yapabilirdik ilgili fonksiyonu yazıp ancak mvc mimarisinde bir kod yazdığımız için bunu böyle yapmak yerine Business tarafında yazmayı tercih ettim. Mantık çünkü çok basit geçmişten günümüze veya günümüzden geçmişe doğru gitmesi çok basit ascending descending koduyla yapılabilir. Ve diğer maddeler de inceleme, güncelleme ve silme işlemleri ki update işleminde yine kayıt işlemindeki gibi save metodu kullanılıyor listeleme zaten olmazsa olmaz burada da var olan listeyi yine findAll ile çekiyoruz silme işleminde de deleteById metoduyla ilgili id'yi göndererek yapıyoruz.

Bunların dışında kurumsal mimari ile yazdım ve test kısmında birim test çerçevesi olan JUnit'i kullandım.

   
   
