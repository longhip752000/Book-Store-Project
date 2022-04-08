CREATE DATABASE [BookStoreABC]
GO

USE [BookStoreABC]
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 7/3/2021 10:05:18 PM ******/

CREATE TABLE [dbo].[Accounts](
	[userName] [varchar](15) NOT NULL,
	[password] [varchar](15) NOT NULL,
	[fullName] [nvarchar](50) NOT NULL,
	[role] [bit] NULL
) ON [PRIMARY]

GO

/****** Object:  Table [dbo].[Books]    Script Date: 7/3/2021 10:05:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Books](
	[bookId] [varchar](10) NOT NULL PRIMARY KEY,
	[bookName] [nvarchar](255) NULL,
	[price] [int] NOT NULL,
	[quanity] [int] NOT NULL,
	[cateId] [varchar](10) NOT NULL)
GO

INSERT [dbo].[Accounts] ([userName], [password], [fullName], [role]) VALUES (N'long', N'long123', N'Bui Viet Long', 1)
INSERT [dbo].[Accounts] ([userName], [password], [fullName], [role]) VALUES (N'qhai', N'qhai123', N'Nguyen Quang Hai', 0)
INSERT [dbo].[Accounts] ([userName], [password], [fullName], [role]) VALUES (N'van2', N'ngocvan123', N'Nguyen Ngoc Van', 0)
INSERT [dbo].[Accounts] ([userName], [password], [fullName], [role]) VALUES (N'congg7', N'cong123', N'Congg Trong', 0)
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'001', N'Book Forum', 114500, 7, N'1')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'002', N'World Literature Today', 300000, 4, N'1')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'003', N'Glimmer Train', 217052, 2, N'1')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'004', N'Publishers Weekly', 98500, 3, N'1')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'005', N'Poets & Writers', 314000, 12, N'1')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'006', N'The Autobiography of Benjamin Franklin', 754800, 3, N'2')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'007', N'Long Walk to Freedom', 412250, 5, N'2')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'008', N'The Diary of a Young Girl', 99800, 8, N'2')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'009', N'The Autobiography of Malcolm X', 142820, 10, N'2')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'010', N'I Know Why the Caged Bird Sings', 456100, 7, N'2')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'011', N' Sherlock Homles', 267210, 5, N'3')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'012', N'Scream  Queens', 78291, 3, N'3')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'013', N'The Davinci Code', 65000, 4, N'3')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'014', N'Dan Brown', 421292, 6, N'3')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'015', N'Gone Girl', 321422, 8, N'3')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'016', N'Oxford Wordpower Dictionary 4th Edition', 124299, 1, N'4')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'017', N'Oxford Anh – OLAD English – Vietnamese', 217000, 4, N'4')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'018', N'Oxford Learner’s Pocket Dictionary', 99000, 2, N'4')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'019', N'Oxford Learner’s Thesaurus', 402011, 7, N'4')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'020', N' Oxford Collocations Dictionary', 528030, 12, N'4')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'021', N'N?u Ch? Còn M?t Ngày Ð? S?ng', 92400, 13, N'5')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'022', N'B?t Tr? Ð?ng Xanh', 168902, 5, N'5')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'023', N'Ngu?i Ðua Di?u', 150000, 9, N'5')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'024', N'Bắt Con Cá Gai', 210800, 3, N'5')
INSERT [dbo].[Books] ([bookId], [bookName], [price], [quanity], [cateId]) VALUES (N'025', N'Ti?ng G?i Noi Hoang Dã', 317600, 8, N'5')


