USE [master]
GO
/****** Object:  Database [SosyalMedya]    Script Date: 3/26/2022 12:28:41 PM ******/
CREATE DATABASE [SosyalMedya]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Kullanicilar', FILENAME = N'C:\Program Files\SQL Server 2019\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Kullanicilar.mdf' , SIZE = 73728KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Kullanicilar_log', FILENAME = N'C:\Program Files\SQL Server 2019\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Kullanicilar_log.ldf' , SIZE = 73728KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SosyalMedya] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SosyalMedya].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SosyalMedya] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SosyalMedya] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SosyalMedya] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SosyalMedya] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SosyalMedya] SET ARITHABORT OFF 
GO
ALTER DATABASE [SosyalMedya] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SosyalMedya] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SosyalMedya] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SosyalMedya] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SosyalMedya] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SosyalMedya] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SosyalMedya] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SosyalMedya] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SosyalMedya] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SosyalMedya] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SosyalMedya] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SosyalMedya] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SosyalMedya] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SosyalMedya] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SosyalMedya] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SosyalMedya] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SosyalMedya] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SosyalMedya] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SosyalMedya] SET  MULTI_USER 
GO
ALTER DATABASE [SosyalMedya] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SosyalMedya] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SosyalMedya] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SosyalMedya] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SosyalMedya] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SosyalMedya] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SosyalMedya] SET QUERY_STORE = OFF
GO
USE [SosyalMedya]
GO
/****** Object:  UserDefinedFunction [dbo].[checkKullaniciID]    Script Date: 3/26/2022 12:28:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[checkKullaniciID](@KullaniciID NVARCHAR(50) )
RETURNS INT 

AS
BEGIN


  IF EXISTS (SELECT 1 FROM Kullanicilar WHERE KullaniciID = @KullaniciID)
    RETURN 1

  RETURN 0
END
GO
/****** Object:  Table [dbo].[Adminler]    Script Date: 3/26/2022 12:28:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Adminler](
	[KullaniciID] [nvarchar](50) NOT NULL,
	[Ad] [nvarchar](50) NOT NULL,
	[Soyad] [nvarchar](50) NOT NULL,
	[Telefon] [nchar](50) NOT NULL,
	[Sifre] [nvarchar](50) NOT NULL,
	[DogumTarihi] [date] NOT NULL,
	[Cinsiyet] [int] NOT NULL,
	[Foto] [varbinary](max) NULL,
 CONSTRAINT [PK_Adminler] PRIMARY KEY CLUSTERED 
(
	[KullaniciID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Arkadaslar]    Script Date: 3/26/2022 12:28:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Arkadaslar](
	[ArkadaslikID] [nvarchar](50) NOT NULL,
	[TalepEdenID] [nvarchar](50) NOT NULL,
	[TalepEdilenID] [nvarchar](50) NOT NULL,
	[Tarih] [date] NOT NULL,
	[Durum] [nchar](10) NOT NULL,
 CONSTRAINT [PK_Arkadaslar] PRIMARY KEY CLUSTERED 
(
	[ArkadaslikID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [Arkadas_TalepEden_TalepEdilen_Unique_Constraint] UNIQUE NONCLUSTERED 
(
	[TalepEdenID] ASC,
	[TalepEdilenID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Begeniler]    Script Date: 3/26/2022 12:28:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Begeniler](
	[PostID] [nvarchar](50) NOT NULL,
	[KullaniciID] [nvarchar](50) NOT NULL,
	[Tarih] [date] NOT NULL,
 CONSTRAINT [Begeni_Post_Kullanici_Unique_Constraint] UNIQUE NONCLUSTERED 
(
	[PostID] ASC,
	[KullaniciID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Kullanicilar]    Script Date: 3/26/2022 12:28:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Kullanicilar](
	[KullaniciID] [nvarchar](50) NOT NULL,
	[Ad] [nvarchar](50) NOT NULL,
	[Soyad] [nvarchar](50) NOT NULL,
	[Telefon] [nchar](50) NOT NULL,
	[Sifre] [nvarchar](50) NOT NULL,
	[DogumTarihi] [date] NOT NULL,
	[Cinsiyet] [int] NOT NULL,
	[Foto] [varbinary](max) NULL,
	[PostSayisi] [int] NULL,
	[TakipSayisi] [int] NULL,
	[ArkadasSayisi] [int] NULL,
 CONSTRAINT [PK_Kullanicilar] PRIMARY KEY CLUSTERED 
(
	[KullaniciID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Mesajlar]    Script Date: 3/26/2022 12:28:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Mesajlar](
	[MesajID] [nvarchar](50) NOT NULL,
	[GonderenID] [nvarchar](50) NOT NULL,
	[AlanID] [nvarchar](50) NOT NULL,
	[Zaman] [datetime] NOT NULL,
	[Metin] [nvarchar](max) NULL,
	[Foto] [varbinary](max) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Postlar]    Script Date: 3/26/2022 12:28:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Postlar](
	[PostID] [nvarchar](50) NOT NULL,
	[KullaniciID] [nvarchar](50) NOT NULL,
	[Tarih] [date] NOT NULL,
	[Metin] [nvarchar](max) NULL,
	[Foto] [varbinary](max) NULL,
	[BegeniSayisi] [int] NULL,
 CONSTRAINT [PK_Postlar] PRIMARY KEY CLUSTERED 
(
	[PostID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Takipler]    Script Date: 3/26/2022 12:28:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Takipler](
	[TakipID] [nvarchar](50) NOT NULL,
	[TakipEdenID] [nvarchar](50) NOT NULL,
	[TakipEdilenID] [nvarchar](50) NOT NULL,
	[Tarih] [date] NOT NULL,
 CONSTRAINT [PK_Takipler] PRIMARY KEY CLUSTERED 
(
	[TakipID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [Takip_TakipEden_TakipEdilen_Unique_Constraint] UNIQUE NONCLUSTERED 
(
	[TakipEdenID] ASC,
	[TakipEdilenID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Yorumlar]    Script Date: 3/26/2022 12:28:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Yorumlar](
	[YorumID] [nvarchar](50) NOT NULL,
	[KullaniciID] [nvarchar](50) NOT NULL,
	[PostID] [nvarchar](50) NOT NULL,
	[Metin] [nvarchar](max) NULL,
	[Foto] [varbinary](max) NULL,
	[Tarih] [datetime] NULL,
 CONSTRAINT [PK_Yorumlar] PRIMARY KEY CLUSTERED 
(
	[YorumID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[Kullanicilar] ADD  CONSTRAINT [DF_Kullanicilar_PostSayisi]  DEFAULT ((0)) FOR [PostSayisi]
GO
ALTER TABLE [dbo].[Kullanicilar] ADD  CONSTRAINT [DF_Kullanicilar_TakipSayisi]  DEFAULT ((0)) FOR [TakipSayisi]
GO
ALTER TABLE [dbo].[Kullanicilar] ADD  CONSTRAINT [DF_Kullanicilar_ArkadasSayisi]  DEFAULT ((0)) FOR [ArkadasSayisi]
GO
ALTER TABLE [dbo].[Postlar] ADD  CONSTRAINT [DF_Postlar_BegeniSayisi]  DEFAULT ((0)) FOR [BegeniSayisi]
GO
ALTER TABLE [dbo].[Yorumlar] ADD  CONSTRAINT [DF_Yorumlar_Tarih]  DEFAULT (getdate()) FOR [Tarih]
GO
ALTER TABLE [dbo].[Arkadaslar]  WITH CHECK ADD  CONSTRAINT [Arkadas_TalepEden_Kullanici_Foreign_Key] FOREIGN KEY([TalepEdenID])
REFERENCES [dbo].[Kullanicilar] ([KullaniciID])
GO
ALTER TABLE [dbo].[Arkadaslar] CHECK CONSTRAINT [Arkadas_TalepEden_Kullanici_Foreign_Key]
GO
ALTER TABLE [dbo].[Arkadaslar]  WITH CHECK ADD  CONSTRAINT [Arkadas_TalepEdilen_Kullanici_Foreign_Key] FOREIGN KEY([TalepEdilenID])
REFERENCES [dbo].[Kullanicilar] ([KullaniciID])
GO
ALTER TABLE [dbo].[Arkadaslar] CHECK CONSTRAINT [Arkadas_TalepEdilen_Kullanici_Foreign_Key]
GO
ALTER TABLE [dbo].[Begeniler]  WITH CHECK ADD  CONSTRAINT [Begeni_Kullanici_Foreign_Key] FOREIGN KEY([KullaniciID])
REFERENCES [dbo].[Kullanicilar] ([KullaniciID])
GO
ALTER TABLE [dbo].[Begeniler] CHECK CONSTRAINT [Begeni_Kullanici_Foreign_Key]
GO
ALTER TABLE [dbo].[Begeniler]  WITH CHECK ADD  CONSTRAINT [Begeni_Post_Foreign_Key] FOREIGN KEY([PostID])
REFERENCES [dbo].[Postlar] ([PostID])
GO
ALTER TABLE [dbo].[Begeniler] CHECK CONSTRAINT [Begeni_Post_Foreign_Key]
GO
ALTER TABLE [dbo].[Mesajlar]  WITH CHECK ADD  CONSTRAINT [Mesajlar_Alan_Kullnaici_ForeignKey] FOREIGN KEY([AlanID])
REFERENCES [dbo].[Kullanicilar] ([KullaniciID])
GO
ALTER TABLE [dbo].[Mesajlar] CHECK CONSTRAINT [Mesajlar_Alan_Kullnaici_ForeignKey]
GO
ALTER TABLE [dbo].[Mesajlar]  WITH CHECK ADD  CONSTRAINT [Mesajlar_Gonderen_Kullnaici_ForeignKey] FOREIGN KEY([GonderenID])
REFERENCES [dbo].[Kullanicilar] ([KullaniciID])
GO
ALTER TABLE [dbo].[Mesajlar] CHECK CONSTRAINT [Mesajlar_Gonderen_Kullnaici_ForeignKey]
GO
ALTER TABLE [dbo].[Postlar]  WITH CHECK ADD  CONSTRAINT [Post_Kullanici_Foreign_Key] FOREIGN KEY([KullaniciID])
REFERENCES [dbo].[Kullanicilar] ([KullaniciID])
GO
ALTER TABLE [dbo].[Postlar] CHECK CONSTRAINT [Post_Kullanici_Foreign_Key]
GO
ALTER TABLE [dbo].[Takipler]  WITH CHECK ADD  CONSTRAINT [Takip_Eden_Kullanici_Foreign_Key] FOREIGN KEY([TakipEdenID])
REFERENCES [dbo].[Kullanicilar] ([KullaniciID])
GO
ALTER TABLE [dbo].[Takipler] CHECK CONSTRAINT [Takip_Eden_Kullanici_Foreign_Key]
GO
ALTER TABLE [dbo].[Takipler]  WITH CHECK ADD  CONSTRAINT [Takip_Edilen_Kullanici_Foreign_Key] FOREIGN KEY([TakipEdilenID])
REFERENCES [dbo].[Kullanicilar] ([KullaniciID])
GO
ALTER TABLE [dbo].[Takipler] CHECK CONSTRAINT [Takip_Edilen_Kullanici_Foreign_Key]
GO
ALTER TABLE [dbo].[Yorumlar]  WITH CHECK ADD  CONSTRAINT [Yorum_Kullnaici_ForeignKey] FOREIGN KEY([KullaniciID])
REFERENCES [dbo].[Kullanicilar] ([KullaniciID])
GO
ALTER TABLE [dbo].[Yorumlar] CHECK CONSTRAINT [Yorum_Kullnaici_ForeignKey]
GO
ALTER TABLE [dbo].[Yorumlar]  WITH CHECK ADD  CONSTRAINT [Yorum_Post_ForeignKey] FOREIGN KEY([PostID])
REFERENCES [dbo].[Postlar] ([PostID])
GO
ALTER TABLE [dbo].[Yorumlar] CHECK CONSTRAINT [Yorum_Post_ForeignKey]
GO
/****** Object:  StoredProcedure [dbo].[KullaniciAdiDogrulamak]    Script Date: 3/26/2022 12:28:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- Belli bir siparişte bulunan ürünlerin toplam miktarını ve toplam tutarını veren sorgu

CREATE PROC [dbo].[KullaniciAdiDogrulamak](@yeniSifre varchar(50),@KullaniciAdi varchar(50))
as

UPDATE Kullanicilar SET Sifre = @yeniSifre  WHERE KullaniciID = @KullaniciAdi
GO
/****** Object:  StoredProcedure [dbo].[SifreGuncelle]    Script Date: 3/26/2022 12:28:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- Belli bir siparişte bulunan ürünlerin toplam miktarını ve toplam tutarını veren sorgu

CREATE PROC [dbo].[SifreGuncelle](@yeniSifre varchar(50),@KullaniciAdi varchar(50))
as

UPDATE Kullanicilar SET Sifre = @yeniSifre  WHERE KullaniciID = @KullaniciAdi
GO
USE [master]
GO
ALTER DATABASE [SosyalMedya] SET  READ_WRITE 
GO
